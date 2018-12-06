package com.professionals.api.Repository;

import com.professionals.api.domain.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.sql.Date;
import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DataSource {

    static ConcurrentMap<Long, Professional> professionalList;
    static ConcurrentMap<Long, User> apiUsersList;

    static void populateDataSource() {


        professionalList = new ConcurrentHashMap<>();
        apiUsersList = new ConcurrentHashMap<>();


        ProfessionalType ceo = new ProfessionalType("CEO");
        ProfessionalType ownerType = new ProfessionalType("OWNER");
        ConcurrentMap<Long, ProfessionalType> professionalTypeCollection = new ConcurrentHashMap<>();
        professionalTypeCollection.put(1L, ceo);
        Professional owner = new Professional("EmptyHeader", "name", "lastName", "myCompany", "username", "+212659594", "test@yopmail.com", "www.test.com", "Rabat", 42.0000, 42.0000, "Morocco", Date.from(Instant.now()), new ConcurrentHashMap<>(professionalTypeCollection), new ConcurrentHashMap<Long, Project>());

        ProjectType projectType = new ProjectType("CDS");
        List<ProjectType> projectTypeList = new ArrayList<>();
        projectTypeList.add(projectType);
        Project project = new Project("header", "title", "description", Period.ofDays(10), owner, projectTypeList);
        ConcurrentMap<Long, Project> projectCollection = new ConcurrentHashMap<>();
        projectCollection.put(1L, project);
        professionalTypeCollection.put(2L, ownerType);
        professionalList.put(1L, new Professional("EmptyHeader", "name", "lastName", "myCompany", "username", "+212659594", "test@yopmail.com", "www.test.com", "Rabat", 30.0000, 30.0000, "Morocco", Date.from(Instant.now()), new ConcurrentHashMap<>(professionalTypeCollection), new ConcurrentHashMap<>(projectCollection)));
        professionalList.put(2L, owner);

        apiUsersList.put(1L, User.builder()
                .username("admin")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build());
        apiUsersList.put(2L, User.builder()
                .username("api")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"))
                .roles(Arrays.asList("ROLE_ADMIN"))
                .build());
    }
}
