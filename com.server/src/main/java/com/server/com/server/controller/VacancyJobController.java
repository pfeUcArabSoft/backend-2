package com.server.com.server.controller;

import com.server.com.server.entity.VacancyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.server.com.server.service.VacancyJobService;

import java.util.List;

@RestController
@RequestMapping("/jobs")

public class VacancyJobController {

    @Autowired
    private VacancyJobService vacancyJobService;

    @PostMapping("/create")
    public VacancyJob create(@RequestParam("jobTitle") String jobTitle,
                             @RequestParam("requiredDegrees") String requiredDegrees,
                             @RequestParam("requiredSkills") String requiredSkills,
                             @RequestParam("requiredExperience") String requiredExperience,
                             @RequestParam("jobBenefits") String jobBenefits,
                             @RequestParam("departments") String departments) {
        return vacancyJobService.createVacancyJob(jobTitle, requiredDegrees, requiredSkills, requiredExperience, jobBenefits, departments);
    }


    @GetMapping("/list")
    public List<VacancyJob> list() {
        return vacancyJobService.getAllVacancyJobs();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VacancyJob> update(@PathVariable Long id, @RequestBody VacancyJob vacancyJobDetails) {

        String jobTitle = vacancyJobDetails.getJobTitle();
        String requiredDegrees = vacancyJobDetails.getRequiredDegrees();
        String requiredSkills = vacancyJobDetails.getRequiredSkills();
        String requiredExperience = vacancyJobDetails.getRequiredExperience();
        String jobBenefits = vacancyJobDetails.getJobBenefits();
        String departments = vacancyJobDetails.getDepartments();

        VacancyJob updatedVacancyJob = vacancyJobService.updateVacancyJob(id, jobTitle, requiredDegrees, requiredSkills, requiredExperience, jobBenefits, departments);

        return ResponseEntity.ok(updatedVacancyJob);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacancyJobService.deleteVacancyJob(id);
        return ResponseEntity.noContent().build();
    }
}