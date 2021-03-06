package com.skowrondariusz.mechanicalprojectmanager.utility;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.*;
import com.skowrondariusz.mechanicalprojectmanager.model.*;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class Mapper {

    private final ManufacturerRepository manufacturerRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public Mapper(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ProcessedPartViewModel convertToProcessedPartViewModel(ProcessedPart entity) {

        return modelMapper.map(entity, ProcessedPartViewModel.class);
    }

    public ProcessedPart convertToProcessedPartEntity(ProcessedPartViewModel viewModel) {

        return modelMapper.map(viewModel, ProcessedPart.class);
    }

    public MechanicalProcessing convertToMechanicalProcessingEntity(MechanicalProcessingViewModel viewModel) {

        return modelMapper.map(viewModel, MechanicalProcessing.class);
    }

    public CommercialPartViewModel convertToCommercialPartViewModel(CommercialPart entity) {

        var viewModel = modelMapper.map(entity, CommercialPartViewModel.class);
        viewModel.setManufacturer(entity.getManufacturer().getName());

        return viewModel;
    }

    public PartsOrder convertToPartsOrdersEntity(PartsOrderViewModel viewModel) {

        var entity = modelMapper.map(viewModel, PartsOrder.class);

        return modelMapper.map(viewModel, PartsOrder.class);
    }

    public PartsOrderViewModel convertToPartsOrderViewModel(PartsOrder entity) {

        var viewModel = modelMapper.map(entity, PartsOrderViewModel.class);
        return viewModel;
    }

    public CommercialPart convertToCommercialPartEntity(CommercialPartViewModel viewModel) {
        var entity = modelMapper.map(viewModel, CommercialPart.class);
        entity.setManufacturer(manufacturerRepository.getManufacturerByName(viewModel.getManufacturer()));
        entity.setType(CommercialPartType.valueOf(viewModel.getType()));
        return entity;
    }

    public Manufacturer convertToManufacturerEntity(ManufacturerViewModel viewModel) {

        return modelMapper.map(viewModel, Manufacturer.class);
    }

    public SalesRepresentativeViewModel convertToSalesRepresentativeViewModel(SalesRepresentative entity) {

        return modelMapper.map(entity, SalesRepresentativeViewModel.class);
    }

    public SalesRepresentative convertToSalesRepresentativeEntity(SalesRepresentativeViewModel viewModel) {

        return modelMapper.map(viewModel, SalesRepresentative.class);
    }

    public ProjectViewModel convertToProjectViewModel(Project entity) {
        var viewModel = modelMapper.map(entity, ProjectViewModel.class);

        if (entity.getPartsOrder() != null) {
            viewModel.setPartsOrder(entity.getPartsOrder().getName());
        }

        return viewModel;
    }

    public Project convertToProjectEntity(ProjectViewModel viewModel) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        var entity = new Project(
                viewModel.getName(),
                Integer.parseInt(viewModel.getProjectNumber()),
                Long.valueOf(viewModel.getBudget())
        );

        try {
            entity.setProjectAssemblingDate(formatter.parse(viewModel.getProjectAssemblingDate()));
            entity.setProjectEndDate(formatter.parse(viewModel.getProjectEndDate()));
            entity.setProjectStartDate(formatter.parse(viewModel.getProjectStartDate()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public Invoice convertToInvoiceEntity(InvoiceViewModel viewModel) {
        return modelMapper.map(viewModel, Invoice.class);
    }
}
