package com.skowrondariusz.mechanicalprojectmanager.utility;

import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.model.SalesRepresentative;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.SalesRepresentativeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
@ConditionalOnProperty(name = "mechanical.database.recreate", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    private ManufacturerRepository manufacturerRepository;
    private SalesRepresentativeRepository salesRepresentativeRepository;
    private ProjectRepository projectRepository;


    public DatabaseInitializer(ManufacturerRepository manufacturerRepository, SalesRepresentativeRepository salesRepresentativeRepository, ProjectRepository projectRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.salesRepresentativeRepository = salesRepresentativeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args){
        var defaultManufacturer = new Manufacturer("UNASSIGNED");
        this.manufacturerRepository.save(defaultManufacturer);

        var defaultSalesRep = new SalesRepresentative("Test", "Test", "test@gmail.com", manufacturerRepository.getManufacturerById(1));
        this.salesRepresentativeRepository.save(defaultSalesRep);

        var defaultProject = new Project("Test Project No1", 1, 45000, new Date(119,10,26,0,0), new Date(119,10,26,0,0), new Date(119,10,26,0,0));
        this.projectRepository.save(defaultProject);



        System.out.println("BASIC DATABASE INITIALIZED");
    }
}
