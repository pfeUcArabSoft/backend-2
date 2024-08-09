package com.server.com.server.record;

import com.server.com.server.entity.Competence;
import com.server.com.server.entity.Diplome;
import com.server.com.server.entity.Experience;
import com.server.com.server.entity.User;

import java.util.Set;

public class UserUpdateRequest {

    private User userDetails;

    private Set<Competence> competencesToAdd;

    private Set<Competence> competencesToRemove;

    private Set<Diplome> diplomesToAdd;
    private Set<Diplome> diplomesToRemove;

    private Set<Experience> experienceToAdd;
    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

    public Set<Competence> getCompetencesToAdd() {
        return competencesToAdd;
    }

    public void setCompetencesToAdd(Set<Competence> competencesToAdd) {
        this.competencesToAdd = competencesToAdd;
    }

    public Set<Competence> getCompetencesToRemove() {
        return competencesToRemove;
    }

    public void setCompetencesToRemove(Set<Competence> competencesToRemove) {
        this.competencesToRemove = competencesToRemove;
    }

    public Set<Diplome> getDiplomesToAdd() {
        return diplomesToAdd;
    }

    public void setDiplomesToAdd(Set<Diplome> diplomesToAdd) {
        this.diplomesToAdd = diplomesToAdd;
    }

    public Set<Diplome> getDiplomesToRemove() {
        return diplomesToRemove;
    }

    public void setDiplomesToRemove(Set<Diplome> diplomesToRemove) {
        this.diplomesToRemove = diplomesToRemove;
    }

    public Set<Experience> getExperienceToAdd() {
        return experienceToAdd;
    }

    public void setExperienceToAdd(Set<Experience> experienceToAdd) {
        this.experienceToAdd = experienceToAdd;
    }
}
