package com.professionals.api.web;

import com.professionals.api.web.dto.MinimumProfessional;
import com.professionals.api.web.dto.ProjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/professionals")
public interface ApiV1 {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    List<MinimumProfessional> displayAllProfessionals();

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    int createProfessional(@RequestBody MinimumProfessional professionalDto);

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    boolean updateProfessional(@RequestBody MinimumProfessional professionalDto);

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    void DeleteProfessional(@PathVariable Long id);

    @GetMapping(value = "/{id}")
    @ResponseBody
    MinimumProfessional displayOneProfessional(@PathVariable long id);

    @GetMapping(params = "dateTime")
    @ResponseBody
    List<MinimumProfessional> displayProfessionalsForGivenPeriod(@RequestParam(value = "dateTime") String timeDate);

    @GetMapping(params = {"longitude", "latitude"})
    @ResponseBody
    List<MinimumProfessional> displayProfessionalsByLocation(@RequestParam(value = "longitude") double longitude, @RequestParam(value = "latitude") double latitude);

    @GetMapping(params = "type")
    @ResponseBody
    List<MinimumProfessional> displayProfessionalsByTypes(@RequestParam(value = "type") List<String> types);

    @GetMapping(value = "/{professionalId}/projects")
    @ResponseBody
    List<ProjectDTO> displayProjectForGivenProfessionals(@PathVariable long professionalId);

    @PostMapping(value = "/{professionalId}/projects")
    @ResponseBody
    int createProjectForProfessional(@PathVariable long professionalId, @RequestBody @Valid ProjectDTO projectDTO);

    @PutMapping(value = "/{professionalId}/projects")
    @ResponseBody
    int updateProjectForProfessional(@PathVariable long professionalId, @RequestBody @Valid ProjectDTO projectDTO);

    @DeleteMapping(value = "/{professionalId}/projects/{projectId}")
    @ResponseBody
    boolean deleteProjectForProfessional(@PathVariable long professionalId, @PathVariable long projectId);

    @GetMapping(value = "/{professionalId}/projects/{projectId}")
    @ResponseBody
    Optional<ProjectDTO> displayProjectForGivenProfessional(@PathVariable long professionalId, @PathVariable long projectId);

    @GetMapping(value = "/{professionalId}/projects", params = {"duration"})
    @ResponseBody
    List<ProjectDTO> displayProjectForGivenProfessionals(@PathVariable long professionalId, @RequestParam(value = "duration") String duration);

    @GetMapping(value = "/{professionalId}/projects", params = {"type"})
    @ResponseBody
    List<ProjectDTO> displayProjectForGivenProfessionalsByTypes(@PathVariable long professionalId, @RequestParam(value = "type") List<String> types);
}
