package com.skowrondariusz.mechanicalprojectmanager.utility;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.*;
import com.skowrondariusz.mechanicalprojectmanager.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class Mapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ProcessedPartViewModel convertToProcessedPartViewModel(ProcessedPart entity){

        return modelMapper.map(entity, ProcessedPartViewModel.class);
    }

    public ProcessedPart convertToProcessedPartEntity(ProcessedPartViewModel viewModel){

        return modelMapper.map(viewModel, ProcessedPart.class);
    }

    public MechanicalProcessing convertToMechanicalProcessingEntity(MechanicalProcessingViewModel viewModel){
    
        return modelMapper.map(viewModel, MechanicalProcessing.class);
    }

    public CommercialPartViewModel convertToCommercialPartViewModel(CommercialPart entity){
    
        var viewModel = modelMapper.map(entity, CommercialPartViewModel.class);
        viewModel.setManufacturer(entity.getManufacturer().getName());

        return viewModel;
    }

    public PartsOrder convertToPartsOrdersEntity(PartsOrderViewModel viewModel){

        var entity = modelMapper.map(viewModel, PartsOrder.class );

        return modelMapper.map(viewModel, PartsOrder.class);
    }

    public PartsOrderViewModel convertToPartsOrderViewModel(PartsOrder entity){

        var viewModel = modelMapper.map(entity, PartsOrderViewModel.class);
        return viewModel;
    }

    public CommercialPart convertToCommercialPartEntity(CommercialPartViewModel viewModel){
        return modelMapper.map(viewModel, CommercialPart.class);
    }

    public Manufacturer convertToManufacturerEntity(ManufacturerViewModel viewModel){

        return modelMapper.map(viewModel, Manufacturer.class);
    }

    public SalesRepresentativeViewModel convertToSalesRepresentativeViewModel(SalesRepresentative entity){

        return modelMapper.map(entity, SalesRepresentativeViewModel.class);
    }

    public SalesRepresentative convertToSalesRepresentativeEntity(SalesRepresentativeViewModel viewModel){

        return modelMapper.map(viewModel, SalesRepresentative.class);
    }

    public ProjectViewModel convertToProjectViewModel(Project entity){
        var viewModel = modelMapper.map(entity, ProjectViewModel.class);

        if(entity.getPartsOrder() != null) {
            viewModel.setPartsOrder(entity.getPartsOrder().getName());
        }

        return viewModel;
    }

    public Project convertToProjectEntity(ProjectViewModel viewModel){
        return modelMapper.map(viewModel, Project.class);
    }
}
