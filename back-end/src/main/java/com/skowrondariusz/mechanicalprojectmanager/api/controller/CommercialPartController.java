package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/commercialParts")
public class CommercialPartController {


    private PartsOrderRepository partsOrderRepository;
    private CommercialPartRepository commercialPartRepository;
    private Mapper mapper;


    public CommercialPartController(PartsOrderRepository partsOrderRepository, CommercialPartRepository commercialPartRepository, Mapper mapper){
        this.partsOrderRepository = partsOrderRepository;
        this.commercialPartRepository = commercialPartRepository;
        this.mapper = mapper;
    }


    @GetMapping("/all")
    public List<CommercialPartViewModel> all(){
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

        var commercialPartViewModel = this.mapper.convertToCommercialPartViewModel(commercialPart);

        return commercialPartViewModel;
    }

    @GetMapping("/byPartsOrders/{partsOrderId}")
    public List<CommercialPartViewModel> byPartsOrders(@PathVariable String partsOrderId){
        List<CommercialPart> commercialParts = new ArrayList<>();

        var partsOrders = this.partsOrderRepository.findById(Long.valueOf(partsOrderId));
        if (partsOrders.isPresent()){
            commercialParts = this.commercialPartRepository.findAllByPartsOrder(partsOrders.get());
        }

        var commercialPartsViewModel = commercialParts.stream()
                .map(commercialPart -> this.mapper.convertToCommercialPartViewModel(commercialPart))
                .collect(Collectors.toList());

        return commercialPartsViewModel;
    }

    @PostMapping
    public CommercialPart save(@RequestBody CommercialPartViewModel commercialPartViewModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException();
        }

        var commercialPartEntity = this.mapper.convertToCommercialPartEntity(commercialPartViewModel);

        this.commercialPartRepository.save(commercialPartEntity);

        return commercialPartEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.commercialPartRepository.deleteById(Long.valueOf(id));
    }

}
