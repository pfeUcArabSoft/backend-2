package com.server.com.server.dao;

import com.server.com.server.entity.VacancyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyJobDao extends JpaRepository<VacancyJob, Long> {

}