package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ManufacturerViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
@CrossOrigin(origins = "http://localhost:4200")
public class ManufacturerController {

    private ManufacturerRepository manufacturerRepository;
    private Mapper mapper;

    public ManufacturerController(ManufacturerRepository manufacturerRepository, Mapper mapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Manufacturer> all() {
        return this.manufacturerRepository.findAll();
    }

    @PostMapping
    public Manufacturer save(@RequestBody ManufacturerViewModel manufacturerViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var manufacturerEntity = this.mapper.convertToManufacturerEntity(manufacturerViewModel);

        this.manufacturerRepository.save(manufacturerEntity);

        return manufacturerEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.manufacturerRepository.deleteById(id);
    }


}
