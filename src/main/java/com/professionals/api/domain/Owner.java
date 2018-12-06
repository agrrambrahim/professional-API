package com.professionals.api.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Owner {

    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String email;
    protected String webSite;
    protected String city;
    private String displayName;
    private String country;
}
