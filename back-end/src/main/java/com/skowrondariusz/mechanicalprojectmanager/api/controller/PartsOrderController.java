package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.PartsOrderViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/partsOrders")
public class PartsOrderController
{


    private PartsOrderRepository partsOrderRepository;
    private Mapper mapper;


    public PartsOrderController(PartsOrderRepository partsOrderRepository, Mapper mapper) {
        this.partsOrderRepository = partsOrderRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<PartsOrder> all() {
        return this.partsOrderRepository.findAll();
    }

    @PostMapping
    public PartsOrder save(@RequestBody PartsOrderViewModel partsOrderViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        var partsOrdersEntity = this.mapper.convertToPartsOrdersEntity(partsOrderViewModel);

        this.partsOrderRepository.save(partsOrdersEntity);

        return partsOrdersEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.partsOrderRepository.deleteById(Long.valueOf(id));
    }


}
