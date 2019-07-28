package com.skowrondariusz.mechanicalprojectmanager.utility;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.MechanicalProcessingViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.PartsOrdersViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ProcessedPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrders;
import com.skowrondariusz.mechanicalprojectmanager.model.ProcessedPart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ProcessedPartViewModel convertToProcessedPartViewModel(ProcessedPart entity){

        var viewModel = modelMapper.map(entity, ProcessedPartViewModel.class);
        return viewModel;
    }

    public ProcessedPart convertToProcessedPartEntity(ProcessedPartViewModel viewModel){

        var entity = modelMapper.map(viewModel, ProcessedPart.class);
        return entity;
    }

    public MechanicalProcessing convertToMechanicalProcessingEntity(MechanicalProcessingViewModel viewModel){

        var entity = modelMapper.map(viewModel, MechanicalProcessing.class);
        return entity;
    }

    public CommercialPartViewModel convertToCommercialPartViewModel(CommercialPart entity){

        var viewModel = modelMapper.map(entity, CommercialPartViewModel.class);
        return viewModel;
    }

    public PartsOrders convertToPartsOrdersEntity(PartsOrdersViewModel viewModel){

        var entity = modelMapper.map(viewModel, PartsOrders.class);
        return entity;
    }

}
