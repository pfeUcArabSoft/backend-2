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
    public ResponseEntity<VacancyJob> create(@RequestBody VacancyJob vacancyJob) {
        VacancyJob createdJob = vacancyJobService.createVacancyJob(vacancyJob);
        return ResponseEntity.ok(createdJob);
    }

    @GetMapping("/list")
    public ResponseEntity<List<VacancyJob>> list() {
        List<VacancyJob> jobs = vacancyJobService.getAllVacancyJobs();
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VacancyJob> update(@PathVariable Long id, @RequestBody VacancyJob vacancyJobDetails) {
        VacancyJob updatedVacancyJob = vacancyJobService.updateVacancyJob(id, vacancyJobDetails);
        return ResponseEntity.ok(updatedVacancyJob);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacancyJobService.deleteVacancyJob(id);
        return ResponseEntity.noContent().build();
    }
}