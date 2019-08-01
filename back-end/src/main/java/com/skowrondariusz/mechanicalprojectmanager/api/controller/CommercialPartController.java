package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrders;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrdersRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/commercialParts")
public class CommercialPartController {


    private PartsOrdersRepository partsOrdersRepository;
    private CommercialPartRepository commercialPartRepository;
    private Mapper mapper;


    public CommercialPartController(PartsOrdersRepository partsOrdersRepository, CommercialPartRepository commercialPartRepository, Mapper mapper){
        this.partsOrdersRepository = partsOrdersRepository;
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

    @GetMapping("/byPartsOrders/{partsOrdersId}")
    public List<CommercialPartViewModel> byPartsOrders(@PathVariable String partsOrdersId){
        List<CommercialPart> commercialParts = new ArrayList<>();

        var partsOrders = this.partsOrdersRepository.findById(Long.valueOf(partsOrdersId));
        if (partsOrders.isPresent()){
            commercialParts = this.commercialPartRepository.findAllByPartsOrders(partsOrders.get());
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
