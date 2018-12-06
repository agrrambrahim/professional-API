package com.professionals.api.configuration;

import com.professionals.api.Repository.DefaultProfessionalRepository;
import com.professionals.api.Repository.DefaultUserRepository;
import com.professionals.api.Repository.ProfessionalRepository;
import com.professionals.api.service.DefaultProfessionalFacade;
import com.professionals.api.service.EntitiesMapper;
import com.professionals.api.service.ProfessionalFacade;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SpringConfiguration {

    @Bean
    public EntitiesMapper entitiesMapper() {
        return new EntitiesMapper(getModelMapper());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ProfessionalRepository professionalRepository() {
        return new DefaultProfessionalRepository();
    }

    @Bean
    public ProfessionalFacade professionalFacade() {
        return new DefaultProfessionalFacade();
    }

    @Bean
    public DefaultUserRepository userRepository() {
        return new DefaultUserRepository();
    }


    @Bean
    public Docket productApi() {
        List<SecurityScheme> securitySchemeList = new ArrayList<>();
        securitySchemeList.add(new BasicAuth("basicAuth"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.professionals.api"))
                .paths(regex("/api/v1/professionals.*"))
                .build().enableUrlTemplating(true).securitySchemes(securitySchemeList);

    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
