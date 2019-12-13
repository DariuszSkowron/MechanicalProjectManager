package com.skowrondariusz.mechanicalprojectmanager.utility;

import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.SalesRepresentative;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.SalesRepresentativeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mechanical.database.recreate", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    private ManufacturerRepository manufacturerRepository;
    private SalesRepresentativeRepository salesRepresentativeRepository;

    public DatabaseInitializer(ManufacturerRepository manufacturerRepository, SalesRepresentativeRepository salesRepresentativeRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.salesRepresentativeRepository = salesRepresentativeRepository;
    }

    @Override
    public void run(String... args){
        var defaultManufacturer = new Manufacturer("UNASSIGNED");
        this.manufacturerRepository.save(defaultManufacturer);

        var defaultSalesRep = new SalesRepresentative("Test", "Test", "test@gmail.com", manufacturerRepository.getManufacturerById(1));
        this.salesRepresentativeRepository.save(defaultSalesRep);



        System.out.println("BASIC DATABASE INITIALIZED");
    }
}
