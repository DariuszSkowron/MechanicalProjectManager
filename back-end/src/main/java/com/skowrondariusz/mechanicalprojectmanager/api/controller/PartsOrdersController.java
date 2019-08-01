package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.PartsOrdersViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrders;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrdersRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/partsOrders")
public class PartsOrdersController {


    private PartsOrdersRepository partsOrdersRepository;
    private Mapper mapper;


    public PartsOrdersController(PartsOrdersRepository partsOrdersRepository, Mapper mapper) {
        this.partsOrdersRepository = partsOrdersRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<PartsOrders> all() {
        return this.partsOrdersRepository.findAll();
    }

    @PostMapping
    public PartsOrders save(@RequestBody PartsOrdersViewModel partsOrdersViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        var partsOrdersEntity = this.mapper.convertToPartsOrdersEntity(partsOrdersViewModel);

        this.partsOrdersRepository.save(partsOrdersEntity);

        return partsOrdersEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.partsOrdersRepository.deleteById(Long.valueOf(id));
    }


}
