package com.skowrondariusz.mechanicalprojectmanager.utility;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ProcessedPartViewModel;
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

}
