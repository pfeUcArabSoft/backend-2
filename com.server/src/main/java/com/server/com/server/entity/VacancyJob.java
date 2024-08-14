package com.server.com.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacancy_job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class VacancyJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    private Date publicationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "required_skills")
    private String requiredSkills;

    @Column(name = "required_degrees")
    private String requiredDegrees;

    @Column(name = "required_experience")
    private String requiredExperience;

    @Column(name = "departments")
    private String departments;

    @Column(name = "job_benefits")
    private String jobBenefits;
}
