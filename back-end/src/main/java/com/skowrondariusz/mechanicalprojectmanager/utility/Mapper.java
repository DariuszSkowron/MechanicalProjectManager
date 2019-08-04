package com.skowrondariusz.mechanicalprojectmanager.utility;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.CommercialPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.MechanicalProcessingViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.PartsOrderViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ProcessedPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import com.skowrondariusz.mechanicalprojectmanager.model.ProcessedPart;
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
    
        return modelMapper.map(entity, CommercialPartViewModel.class);
    }

    public PartsOrder convertToPartsOrdersEntity(PartsOrderViewModel viewModel){
    
        return modelMapper.map(viewModel, PartsOrder.class);
    }

    public CommercialPart convertToCommercialPartEntity(CommercialPartViewModel viewModel){
        return modelMapper.map(viewModel, CommercialPart.class);
    }
}
