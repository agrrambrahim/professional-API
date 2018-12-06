package com.professionals.api.service;

import com.professionals.api.Repository.ProfessionalRepository;
import com.professionals.api.domain.Professional;
import com.professionals.api.domain.ProfessionalType;
import com.professionals.api.domain.Project;
import com.professionals.api.web.dto.MinimumProfessional;
import com.professionals.api.web.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DefaultProfessionalFacade implements ProfessionalFacade {

    @Autowired
    private EntitiesMapper entitiesMapper;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public Optional<MinimumProfessional> getProfessional(long id) {
        return Optional.ofNullable(entitiesMapper.toProfessionalDto(professionalRepository.findById(id).get()));
    }

    @Override
    public List<MinimumProfessional> getProfessionalsForGivenPeriod(Date date) {
        return professionalRepository.findByUpdatedDate(date).stream().map(entitiesMapper::toProfessionalDto).collect(Collectors.toList());
    }

    @Override
    public List<MinimumProfessional> getProfessionalsByLocation(double longitude, double latitude) {
        return professionalRepository.findByLongitudeAndLatitude(longitude, latitude).stream().map(entitiesMapper::toProfessionalDto).collect(Collectors.toList());
    }


    private List<MinimumProfessional> getProfessionalsByProfessionalTypes(List<ProfessionalType> types) {
        return professionalRepository.findByTypes(types).stream().map(entitiesMapper::toProfessionalDto).collect(Collectors.toList());
    }

    @Override
    public List<MinimumProfessional> getAllProfessionals() {
        return professionalRepository.findAll().entrySet().stream().map(entitiesMapper::toProfessionalDto).collect(Collectors.toList());
    }

    @Override
    public int createProfessional(MinimumProfessional professionalDto) {
        try {
            Professional professional = entitiesMapper.toProfessionalEntity(professionalDto);
            return professionalRepository.save(professional);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateProfessional(MinimumProfessional professionalDto) {
        try {
            Professional professional = entitiesMapper.toProfessionalEntity(professionalDto);
            professionalRepository.save(professional);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProfessional(Long id) {
        return professionalRepository.delete(id);
    }

    @Override
    public List<ProjectDTO> displayProjectForGivenProfessionals(long professionalId) {
        return professionalRepository.findProjectsByProfessionals(professionalId).values().stream().map(project -> entitiesMapper.toProjectsDto(project)).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectDTO> displayOneProject(long professionalId, long projectId) {
        Optional<Project> project = professionalRepository.findProject(professionalId, projectId);
        if (project.isPresent())
            return Optional.of(entitiesMapper.toProjectsDto(project.get()));
        return Optional.empty();
    }

    @Override
    public List<ProjectDTO> displayProjectsByDuration(long professionalId, Period date) {
        return professionalRepository.findProjectsByDuration(professionalId, date).stream().map(project -> entitiesMapper.toProjectsDto(project)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> displayProjectsByTypes(long professionalId, List<String> types) {
        return professionalRepository.findProjectsByTypes(professionalId, types).stream().map(project -> entitiesMapper.toProjectsDto(project)).collect(Collectors.toList());
    }

    @Override
    public int createProject(long professionalId, ProjectDTO projectDTO) {
        Project project = entitiesMapper.toProjectEntity(projectDTO);
        return professionalRepository.saveProject(professionalId, project);
    }

    @Override
    public int updateProject(long professionalId, ProjectDTO projectDTO) {
        Project project = entitiesMapper.toProjectEntity(projectDTO);
        return professionalRepository.updateProjectForProfessional(professionalId, project);
    }

    @Override
    public boolean deleteProject(long professionalId, long projectId) {
        return professionalRepository.deleteProjectFor(professionalId, projectId);

    }

    @Override
    public List<MinimumProfessional> getProfessionalsByTypes(List<String> strings) {
        List<ProfessionalType> professionalTypeList = strings.stream().map(typeName -> new ProfessionalType(typeName)).collect(Collectors.toList());
        return getProfessionalsByProfessionalTypes(professionalTypeList);
    }


}
