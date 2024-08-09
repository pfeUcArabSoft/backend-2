package com.server.com.server.dao;


import com.server.com.server.entity.Competence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceDao extends CrudRepository<Competence, String> {
    List<Competence> findByName(String name);


}
