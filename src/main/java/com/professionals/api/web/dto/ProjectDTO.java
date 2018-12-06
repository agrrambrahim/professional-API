package com.professionals.api.web.dto;

import com.professionals.api.domain.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String name;
    @Builder.Default
    private List<ProjectType> projectTypes = new ArrayList<>();
    private OwnerDTO owner;

}
