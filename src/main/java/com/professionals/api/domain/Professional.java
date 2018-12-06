package com.professionals.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Professional extends Owner {
    private static AtomicLong projectIdsAtomic = new AtomicLong(0);
    private static AtomicLong professionalTypesIdsAtomic = new AtomicLong(0);
    private Long id;
    private String header;
    private String companyName;
    private String userName;
    private double longitude;
    private double latitude;
    private Date updatedDate;
    private Map<Long, ProfessionalType> types;
    private Map<Long, Project> projects;

    public Professional(String header, String firstName, String lastName, String companyName, String userName, String phone, String email, String webSite, String city, double longitude, double latitude, String country, Date updatedDate, Map<Long, ProfessionalType> types, Map<Long, Project> projects) {
        super(firstName, lastName, phone, email, webSite, city, firstName + lastName, country);
        this.header = header;
        this.companyName = companyName;
        this.userName = userName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updatedDate = updatedDate;
        this.types = types;
        this.projects = projects;
    }

    public void addProjectIfMissing(Project project) {
        if (projects.containsValue(project) == false)
            projects.put(projectIdsAtomic.getAndIncrement(), project);
    }

    public void addProfessionalTypeIfMissing(ProfessionalType professionalType) {
        if (types.containsValue(professionalType) == false)
            types.put(professionalTypesIdsAtomic.getAndIncrement(), professionalType);
    }

    public void updateProject(Project project) {
        Optional<Long> key = this.projects.entrySet().stream().filter(projectEntry -> projectEntry.getValue().equals(project)).map(projectEntry -> projectEntry.getKey()).findFirst();
        if (key.isPresent())
            this.projects.replace(key.get(), project);
        else
            addProjectIfMissing(project);
    }
}

