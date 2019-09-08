package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.PartsOrderViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/partsOrders")
public class PartsOrderController
{


    private PartsOrderRepository partsOrderRepository;
    private ProjectRepository projectRepository;
    private Mapper mapper;


    public PartsOrderController(PartsOrderRepository partsOrderRepository, ProjectRepository projectRepository, Mapper mapper) {
        this.partsOrderRepository = partsOrderRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<PartsOrderViewModel> all() {
        var partsOrders = this.partsOrderRepository.findAll();

        return partsOrders.stream()
                .map(order -> this.mapper.convertToPartsOrderViewModel(order))
                .collect(Collectors.toList());


//        public List<CommercialPartViewModel> all(){
//            var commercialParts = this.commercialPartRepository.findAll();
//
//            return commercialParts.stream()
//                    .map(part -> this.mapper.convertToCommercialPartViewModel(part))
//                    .collect(Collectors.toList());
    }

    @PostMapping
    public PartsOrder save(@RequestBody PartsOrderViewModel partsOrderViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        var partsOrdersEntity = this.mapper.convertToPartsOrdersEntity(partsOrderViewModel);
//        partsOrdersEntity.setProject(projectRepository.findById(Long.valueOf(partsOrderViewModel.getProject())));
        this.partsOrderRepository.save(partsOrdersEntity);

        if (partsOrdersEntity.getProject() != null) {
            var map = this.projectRepository.findByPartsOrder(partsOrdersEntity);
            map.setPartsOrder(partsOrdersEntity);
            this.projectRepository.save(map);
            System.out.println("test");

        }

        return partsOrdersEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.partsOrderRepository.deleteById(Long.valueOf(id));
    }


}
