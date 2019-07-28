package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrders;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrdersRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        var commercialPartModel = commercialParts.stream()
                .map(part -> this.mapper.convertToCommercialPartViewModel(part))
                .collect(Collectors.toList());

        return commercialPartModel;
    }

}
