package com.server.com.server.service;

import com.server.com.server.dao.VacancyJobDao;
import com.server.com.server.entity.VacancyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyJobService {

    @Autowired
    private VacancyJobDao vacancyJobDao;

    public VacancyJob createVacancyJob(VacancyJob vacancyJob) {
        vacancyJob.setPublicationDate(new Date());
        vacancyJob.setDeadline(calculateDeadline());
        return vacancyJobDao.save(vacancyJob);
    }

    public Optional<VacancyJob> getVacancyJobById(Long id) {
        return vacancyJobDao.findById(id);
    }

    public List<VacancyJob> getAllVacancyJobs() {
        return vacancyJobDao.findAll();
    }

    public VacancyJob updateVacancyJob(Long id, VacancyJob vacancyJobDetails) {
        return vacancyJobDao.findById(id).map(vacancyJob -> {
            vacancyJob.setJobTitle(vacancyJobDetails.getJobTitle());
            vacancyJob.setRequiredSkills(vacancyJobDetails.getRequiredSkills());
            vacancyJob.setRequiredDegrees(vacancyJobDetails.getRequiredDegrees());
            vacancyJob.setRequiredExperience(vacancyJobDetails.getRequiredExperience());
            vacancyJob.setJobBenefits(vacancyJobDetails.getJobBenefits());
            vacancyJob.setDepartments(vacancyJobDetails.getDepartments());
            vacancyJob.setPublicationDate(new Date());
            vacancyJob.setDeadline(calculateDeadline());
            return vacancyJobDao.save(vacancyJob);
        }).orElseThrow(() -> new RuntimeException("VacancyJob not found with id: " + id));
    }

    public void deleteVacancyJob(Long id) {
        if (vacancyJobDao.existsById(id)) {
            vacancyJobDao.deleteById(id);
        } else {
            throw new RuntimeException("VacancyJob not found with id: " + id);
        }
    }

    private Date calculateDeadline() {
        Date deadline = new Date();
        deadline.setTime(deadline.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 days from now
        return deadline;
    }
}