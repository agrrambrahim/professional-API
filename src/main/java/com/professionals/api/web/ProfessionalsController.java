package com.professionals.api.web;

import com.professionals.api.service.ProfessionalFacade;
import com.professionals.api.web.dto.MinimumProfessional;
import com.professionals.api.web.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.professionals.api.web.DateParser.getPeriodFrom;

@RestController
public class ProfessionalsController implements ApiV1 {

    @Autowired
    private ProfessionalFacade professionalFacade;

    @Override
    public List<MinimumProfessional> displayAllProfessionals() {
        return professionalFacade.getAllProfessionals();
    }

    @Override
    public int createProfessional(@RequestBody MinimumProfessional professionalDto) {
        return professionalFacade.createProfessional(professionalDto);
    }

    @Override
    public boolean updateProfessional(@RequestBody MinimumProfessional professionalDto) {
        return professionalFacade.updateProfessional(professionalDto);
    }

    @Override
    public void DeleteProfessional(@PathVariable Long id) {
        professionalFacade.deleteProfessional(id);
    }

    @Override
    public MinimumProfessional displayOneProfessional(@PathVariable long id) {
        if (professionalFacade.getProfessional(id).isPresent())
            return professionalFacade.getProfessional(id).get();
        else
            throw new NoSuchElementException("No professional with id =" + id + " is found ");
    }

    @Override
    public List<MinimumProfessional> displayProfessionalsForGivenPeriod(@RequestParam(value = "dateTime") String timeDate) {
        Date updatedDate = DateParser.fromString(timeDate);
        return professionalFacade.getProfessionalsForGivenPeriod(updatedDate);
    }

    @Override
    public List<MinimumProfessional> displayProfessionalsByLocation(@RequestParam(value = "longitude") double longitude, @RequestParam(value = "latitude") double latitude) {
        return professionalFacade.getProfessionalsByLocation(longitude, latitude);
    }

    @Override
    public List<MinimumProfessional> displayProfessionalsByTypes(@RequestParam(value = "type") List<String> types) {
        return professionalFacade.getProfessionalsByTypes(types);
    }

    @Override
    public List<ProjectDTO> displayProjectForGivenProfessionals(@PathVariable long professionalId) {
        return professionalFacade.displayProjectForGivenProfessionals(professionalId);
    }

    @Override
    public int createProjectForProfessional(@PathVariable long professionalId, @RequestBody @Valid ProjectDTO projectDTO) {
        return professionalFacade.createProject(professionalId, projectDTO);
    }

    @Override
    public int updateProjectForProfessional(@PathVariable long professionalId, @RequestBody @Valid ProjectDTO projectDTO) {
        return professionalFacade.updateProject(professionalId, projectDTO);
    }

    @Override
    public boolean deleteProjectForProfessional(@PathVariable long professionalId, @PathVariable long projectId) {
        return professionalFacade.deleteProject(professionalId, projectId);
    }

    @Override
    public Optional<ProjectDTO> displayProjectForGivenProfessional(@PathVariable long professionalId, @PathVariable long projectId) {
        return professionalFacade.displayOneProject(professionalId, projectId);
    }

    @Override
    public List<ProjectDTO> displayProjectForGivenProfessionals(@PathVariable long professionalId, @RequestParam(value = "duration") String duration) {
        return professionalFacade.displayProjectsByDuration(professionalId, getPeriodFrom(duration));
    }

    @Override
    public List<ProjectDTO> displayProjectForGivenProfessionalsByTypes(@PathVariable long professionalId, @RequestParam(value = "type") List<String> types) {
        return professionalFacade.displayProjectsByTypes(professionalId, types);
    }


}
