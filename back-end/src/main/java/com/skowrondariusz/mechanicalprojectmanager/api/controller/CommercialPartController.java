package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPartType;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.service.CommercialPartService;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/commercialParts")
public class CommercialPartController {


    @Autowired
    private CommercialPartService commercialPartService;
    private PartsOrderRepository partsOrderRepository;
    private CommercialPartRepository commercialPartRepository;
    private Mapper mapper;
    private ManufacturerRepository manufacturerRepository;


    public CommercialPartController(PartsOrderRepository partsOrderRepository, CommercialPartRepository commercialPartRepository, Mapper mapper, ManufacturerRepository manufacturerRepository) {
        this.partsOrderRepository = partsOrderRepository;
        this.commercialPartRepository = commercialPartRepository;
        this.mapper = mapper;
        this.manufacturerRepository = manufacturerRepository;
    }


    @GetMapping("/all")
    public List<CommercialPartViewModel> all() {
        var commercialParts = this.commercialPartRepository.findAll();

        return commercialParts.stream()
                .map(part -> this.mapper.convertToCommercialPartViewModel(part))
                .collect(Collectors.toList());


    }

    @GetMapping("/byId/{id}")
    public CommercialPartViewModel byId(@PathVariable String id) {
        var commercialPart = this.commercialPartRepository.findById(Long.valueOf(id)).orElse(null);

        if (commercialPart == null) {
            throw new EntityNotFoundException();
        }

        return this.mapper.convertToCommercialPartViewModel(commercialPart);
    }

    @GetMapping("/byPartsOrder/{partsOrderId}")
    public List<CommercialPartViewModel> byPartsOrders(@PathVariable String partsOrderId) {
        List<CommercialPart> commercialParts = new ArrayList<>();

        var partsOrder = this.partsOrderRepository.findById(Long.valueOf(partsOrderId));
        if (partsOrder.isPresent()) {
            commercialParts = this.commercialPartRepository.findAllByPartsOrder(partsOrder.get());
        }

        return commercialParts.stream()
                .map(commercialPart -> this.mapper.convertToCommercialPartViewModel(commercialPart))
                .collect(Collectors.toList());
    }

    @PostMapping
    public CommercialPart save(@RequestBody CommercialPartViewModel commercialPartViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var commercialPartEntity = this.mapper.convertToCommercialPartEntity(commercialPartViewModel);
        commercialPartEntity.setDeliveryDate(commercialPartService.checkDeliveryDate(commercialPartEntity.getPartsOrder().getId()));
        this.commercialPartRepository.save(commercialPartEntity);
        return commercialPartEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.commercialPartRepository.deleteById(Long.valueOf(id));
    }


    @GetMapping("/types")
    public List<CommercialPartType> allCommercialPartsTypes() {
        List<CommercialPartType> enums = Arrays.asList(CommercialPartType.values());
        return enums;
    }
}
