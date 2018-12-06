package com.professionals.api.Repository;

import com.professionals.api.domain.Professional;
import com.professionals.api.domain.ProfessionalType;
import com.professionals.api.domain.Project;

import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static com.professionals.api.Repository.DataSource.professionalList;
import static java.util.Optional.ofNullable;

public class DefaultProfessionalRepository implements ProfessionalRepository {


    public DefaultProfessionalRepository() {
        DataSource.populateDataSource();
    }

    @Override
    public List<Professional> findByLongitudeAndLatitude(double longitude, double latitude) {
        return DataSource.professionalList.values().stream().filter(professional -> professional.getLatitude() == latitude && professional.getLongitude() == longitude).collect(Collectors.toList());
    }

    @Override
    public List<Professional> findByTypes(List<ProfessionalType> types) {
        return DataSource.professionalList.values().stream().filter(professional -> professional.getTypes().values().containsAll(types)).collect(Collectors.toList());
    }

    @Override
    public List<Professional> findByUpdatedDate(Date date) {
        return DataSource.professionalList.values().stream().filter(professional -> professional.getUpdatedDate().before(date)).collect(Collectors.toList());
    }

    @Override
    public Optional<Professional> findById(long id) {
        return ofNullable(professionalList.get(id));
    }

    @Override
    public Map<Long, Professional> findAll() {
        return professionalList;
    }

    @Override
    public int save(Professional professional) {
        int newId = professionalList.size();
        newId++;
        professionalList.put((long) newId, professional);
        return newId;
    }

    @Override
    public boolean delete(long id) {
        if (professionalList.containsKey(id)) {
            return professionalList.remove(id, professionalList.get(id));
        }
        return false;
    }

    @Override
    public Map<Long, Project> findProjectsByProfessionals(long professionalId) {
        if (professionalList.containsKey(professionalId))
            return new HashMap<>(professionalList.get(professionalId).getProjects());
        else
            return new HashMap<>();
    }

    @Override
    public Optional<Project> findProject(long professionalId, long projectId) {
        if (professionalList.containsKey(professionalId))
            return ofNullable(professionalList.get(professionalId).getProjects().get(projectId));
        return Optional.empty();
    }

    @Override
    public List<Project> findProjectsByDuration(long professionalId, Period date) {
        return professionalList.get(professionalId).getProjects().values().stream().filter(project -> project.getDuration().equals(date)).collect(Collectors.toList());
    }

    @Override
    public List<Project> findProjectsByTypes(long professionalId, List<String> types) {
        return professionalList.get(professionalId).getProjects().values().stream().filter(project -> types.containsAll(project.getTypes().stream().map(type -> type.getName()).collect(Collectors.toList()))).collect(Collectors.toList());

    }

    @Override
    public int saveProject(long professionalId, Project project) {
        professionalList.get(professionalId).addProjectIfMissing(project);
        return 1;
    }

    @Override
    public int updateProjectForProfessional(long professionalId, Project project) {
        professionalList.get(professionalId).updateProject(project);
        return 1;
    }

    @Override
    public boolean deleteProjectFor(long professionalId, long projectId) {
        return professionalList.get(professionalId).getProjects().remove(projectId, professionalList.get(professionalId).getProjects().get(projectId));
    }
}
