package com.skowrondariusz.mechanicalprojectmanager.utility;

import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mechanical.database.recreate", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    private ManufacturerRepository manufacturerRepository;

    public DatabaseInitializer(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void run(String... args){
        var defaultManufacturer = new Manufacturer("UNASSIGNED");
        this.manufacturerRepository.save(defaultManufacturer);


        System.out.println("BASIC DATABASE INITIALIZED");
    }
}
