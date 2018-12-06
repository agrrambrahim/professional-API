package com.professionals.api.domain;


import lombok.*;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Project {
    private String header;
    private String title;
    private String description;
    private Period duration;
    private Owner owner;
    @Builder.Default
    private List<ProjectType> types = new ArrayList<>();
}

