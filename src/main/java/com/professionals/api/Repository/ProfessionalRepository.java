package com.professionals.api.Repository;

import com.professionals.api.domain.Professional;
import com.professionals.api.domain.ProfessionalType;
import com.professionals.api.domain.Project;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProfessionalRepository {

    List<Professional> findByLongitudeAndLatitude(double longitude, double latitude);

    List<Professional> findByTypes(List<ProfessionalType> types);

    List<Professional> findByUpdatedDate(Date date);

    Optional<Professional> findById(long id);

    Map<Long, Professional> findAll();

    int save(Professional professional);

    boolean delete(long id);

    Map<Long, Project> findProjectsByProfessionals(long professionalId);

    Optional<Project> findProject(long professionalId, long projectId);

    List<Project> findProjectsByDuration(long professionalId, Period date);

    List<Project> findProjectsByTypes(long professionalId, List<String> types);

    int saveProject(long professionalId, Project project);

    int updateProjectForProfessional(long professionalId, Project project);

    boolean deleteProjectFor(long professionalId, long projectId);

}
