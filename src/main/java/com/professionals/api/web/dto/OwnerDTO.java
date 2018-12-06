package com.professionals.api.web.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OwnerDTO implements Serializable {

    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String email;
    protected String webSite;
    protected String city;
    private String displayName;
    private String country;

    public String getDisplayName() {
        if (displayName.isEmpty())
            return firstName + lastName;
        return displayName;
    }
}
