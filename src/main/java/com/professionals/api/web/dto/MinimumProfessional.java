package com.professionals.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MinimumProfessional extends OwnerDTO implements Serializable {

    @NotNull
    private String companyName;
    private String userName;
    private double longitude;
    private double latitude;

    private Map<Long, ProfessionalTypeDTO> types;

    private Map<Long, ProjectDTO> projects;

}
