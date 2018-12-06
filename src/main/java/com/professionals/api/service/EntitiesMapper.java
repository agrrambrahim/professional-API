package com.professionals.api.service;

import com.professionals.api.domain.Professional;
import com.professionals.api.domain.ProfessionalType;
import com.professionals.api.domain.Project;
import com.professionals.api.domain.ProjectType;
import com.professionals.api.web.dto.MinimumProfessional;
import com.professionals.api.web.dto.ProfessionalTypeDTO;
import com.professionals.api.web.dto.ProjectDTO;
import com.professionals.api.web.dto.ProjectTypeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Map;

public class EntitiesMapper {


    private ModelMapper modelMapper;

    @Autowired
    public EntitiesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(Professional.class, MinimumProfessional.class);
        this.modelMapper.createTypeMap(ProfessionalType.class, ProfessionalTypeDTO.class);
        this.modelMapper.createTypeMap(Project.class, ProjectDTO.class);
        this.modelMapper.createTypeMap(ProjectType.class, ProjectTypeDTO.class);
    }

    public MinimumProfessional toProfessionalDto(Professional professional) {
        MinimumProfessional dto = modelMapper.map(professional, MinimumProfessional.class);
        return dto;
    }

    public MinimumProfessional toProfessionalDto(Map.Entry<Long, Professional> professionalEntry) {

        MinimumProfessional dto = modelMapper.map(professionalEntry.getValue(), MinimumProfessional.class);
        return dto;
    }


    public Professional toProfessionalEntity(MinimumProfessional professionalDto) throws ParseException {

        Professional professional = modelMapper.map(professionalDto, Professional.class);
        //professionalDto.getTypes().values().stream().map(type -> new ProfessionalType(type.getName())).forEach(professionalType -> professional.addProfessionalTypeIfMissing(professionalType));
        return professional;
    }

    public ProjectDTO toProjectsDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public Project toProjectEntity(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }

}
