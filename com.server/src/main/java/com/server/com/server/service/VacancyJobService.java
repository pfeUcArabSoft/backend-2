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

    public VacancyJob createVacancyJob(String jobTitle, String requiredSkills, String requiredDegrees, String requiredExperience, String jobBenefits, String departments) {

        VacancyJob vacancyJob = new VacancyJob();

        vacancyJob.setJobTitle(jobTitle);
        vacancyJob.setRequiredSkills(requiredSkills);
        vacancyJob.setRequiredDegrees(requiredDegrees);
        vacancyJob.setRequiredExperience(requiredExperience);
        vacancyJob.setJobBenefits(jobBenefits);
        vacancyJob.setDepartments(departments);

        vacancyJob.setPublicationDate(new Date());
        Date deadline = new Date();
        deadline.setTime(deadline.getTime() + (30L * 24 * 60 * 60 * 1000));
        vacancyJob.setDeadline(deadline);

        return vacancyJobDao.save(vacancyJob);
    }


    public Optional<VacancyJob> getVacancyJobById(Long id) {
        return vacancyJobDao.findById(id);
    }

    public List<VacancyJob> getAllVacancyJobs() {
        return vacancyJobDao.findAll();
    }

    public VacancyJob updateVacancyJob(Long id, String jobTitle, String requiredSkills, String requiredDegrees, String requiredExperience, String jobBenefits, String departments) {
        Optional<VacancyJob> vaOptional=vacancyJobDao.findById(id);
        if(vaOptional.isPresent()) {

            VacancyJob vaJob=vaOptional.get();

            vaJob.setJobTitle(jobTitle);
            vaJob.setRequiredSkills(requiredSkills);
            vaJob.setRequiredDegrees(requiredDegrees);
            vaJob.setRequiredExperience(requiredExperience);
            vaJob.setJobBenefits(jobBenefits);
            vaJob.setDepartments(departments);



            vaJob.setPublicationDate(new Date());
            Date deadline = new Date();
            deadline.setTime(deadline.getTime() + (30L * 24 * 60 * 60 * 1000));
            vaJob.setDeadline(deadline);

            return vacancyJobDao.save(vaJob);

        }
        return null;
    }

    public void deleteVacancyJob(Long id) {
        if (vacancyJobDao.existsById(id)) {
            vacancyJobDao.deleteById(id);
        } else {
            throw new RuntimeException("VacancyJob not found with id: " + id);
        }
    }
}
