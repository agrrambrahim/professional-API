package com.professionals.api.service;


import com.professionals.api.web.dto.MinimumProfessional;
import com.professionals.api.web.dto.ProjectDTO;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProfessionalFacade {
    public Optional<MinimumProfessional> getProfessional(long id);

    public List<MinimumProfessional> getProfessionalsForGivenPeriod(Date date);

    public List<MinimumProfessional> getProfessionalsByLocation(double longitude, double latitude);

    public List<MinimumProfessional> getProfessionalsByTypes(List<String> types);

    public List<MinimumProfessional> getAllProfessionals();

    public int createProfessional(MinimumProfessional professionalDto);

    public boolean updateProfessional(MinimumProfessional professionalDto);

    public boolean deleteProfessional(Long id);

    public List<ProjectDTO> displayProjectForGivenProfessionals(long professionalId);

    public Optional<ProjectDTO> displayOneProject(long professionalId, long projectId);

    public List<ProjectDTO> displayProjectsByDuration(long professionalId, Period date);

    public List<ProjectDTO> displayProjectsByTypes(long professionalId, List<String> types);

    public int createProject(long professionalId, ProjectDTO projectDTO);

    public int updateProject(long professionalId, ProjectDTO projectDTO);

    public boolean deleteProject(long professionalId, long projectId);

}
