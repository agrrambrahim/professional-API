package com.professionals.api.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProfessionalType {
    @NotNull
    private String name;
}
