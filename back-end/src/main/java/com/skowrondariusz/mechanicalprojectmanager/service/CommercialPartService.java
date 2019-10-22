package com.skowrondariusz.mechanicalprojectmanager.service;

import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CommercialPartService {

    private CommercialPartRepository commercialPartRepository;
    private ProjectRepository projectRepository;
    private PartsOrderRepository partsOrderRepository;

    public CommercialPartService(CommercialPartRepository commercialPartRepository, ProjectRepository projectRepository, PartsOrderRepository partsOrderRepository){
        this.commercialPartRepository = commercialPartRepository;
        this.projectRepository = projectRepository;
        this.partsOrderRepository = partsOrderRepository;
    }

    public Date checkDeliveryDate(Long partsOrderId){
        Date partCreationDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date projectDate = this.projectRepository.findByPartsOrder(partsOrderRepository.getOne(partsOrderId)).getProjectAssemblingDate();

        if (projectDate == null)
            throw new IllegalArgumentException("Date of project assembling phase cant be unassigned");
        if(partCreationDate.before(projectDate)){
            return projectDate;
        }
        else return projectDate;
    }


}
