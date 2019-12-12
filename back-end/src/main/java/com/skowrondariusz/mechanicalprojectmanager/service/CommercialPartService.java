package com.skowrondariusz.mechanicalprojectmanager.service;

import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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
        Calendar c = Calendar.getInstance();

        Date projectDate = this.projectRepository.findByPartsOrder(partsOrderRepository.findByProjectId(partsOrderId)).getProjectAssemblingDate();

        if (projectDate == null)
            throw new NullPointerException("Date of project assembling phase can't be unassigned");
        if(partCreationDate.before(projectDate)){
            return projectDate;
        }
        c.setTime(partCreationDate);
        c.add(Calendar.DATE, 2 );
        partCreationDate = c.getTime();
        return partCreationDate;
    }


}
