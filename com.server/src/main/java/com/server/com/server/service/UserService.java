package com.server.com.server.service;

import com.server.com.server.dao.RoleDao;
import com.server.com.server.dao.UserDao;
import com.server.com.server.entity.Competence;
import com.server.com.server.entity.Diplome;
import com.server.com.server.entity.Role;
import com.server.com.server.entity.User;
import com.server.com.server.record.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public User registerNewUser(User user) {
//        User userExist = userDao.findById(user.getUserName()).get();
//        if(userExist != null){
//            try {
//                throw new Exception("user Exist");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("administrator");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


        User user = new User();
        user.setUserFirstName("nour");
        user.setUserLastName("ghada");
        user.setUserName("nour123");
        user.setUserPassword(getEncodedPassword("ghada@pass"));
        Set<Role> userRoles = new HashSet<>();
        user.setRole(userRoles);
        userRoles.add(userRole);
        userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User getUserByUserName(String userName) {
        return userDao.findById(userName).orElse(null);
    }

    public void deleteUserByUserName(String userName) {
        if (userDao.existsById(userName)) {
            userDao.deleteById(userName);
        } else {
            throw new RuntimeException("User not found with userName: " + userName);
        }
    }

    public User updateUserByUserName(String userName, UserUpdateRequest userUpdateRequest, User oldValueUser) {
        return userDao.findById(userName).map(user -> {
            if (userUpdateRequest.getUserDetails().getUserFirstName() != null) {
                user.setUserFirstName(userUpdateRequest.getUserDetails().getUserFirstName());
            }
            if (userUpdateRequest.getUserDetails().getUserLastName() != null) {
                user.setUserLastName(userUpdateRequest.getUserDetails().getUserLastName());
            }
            if (userUpdateRequest.getUserDetails().getUserPassword() != null && !userUpdateRequest.getUserDetails().getUserPassword().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(userUpdateRequest.getUserDetails().getUserPassword()));
            }

            //competences section
            if (userUpdateRequest.getCompetencesToAdd() != null && userUpdateRequest.getCompetencesToAdd().size() > 0) {
//                boolean competenceExists = oldValueUser.getCompetences().stream()
//                        .anyMatch(c -> c.getName().equals(((Competence) userUpdateRequest.getCompetencesToAdd().toArray()[0]).getName()));
//                if (!competenceExists) {
//                    userUpdateRequest.getCompetencesToAdd().addAll(oldValueUser.getCompetences());
//                }
                userUpdateRequest.getCompetencesToAdd().addAll(oldValueUser.getCompetences());
            }
            if (userUpdateRequest.getCompetencesToRemove() != null && userUpdateRequest.getCompetencesToRemove().size() > 0) {
                Set<String> namesToRemove = userUpdateRequest.getCompetencesToRemove()
                        .stream()
                        .map(Competence::getName)
                        .collect(Collectors.toSet());
                Set<Competence> filteredCompetences = oldValueUser.getCompetences().stream()
                        .filter(competence -> !namesToRemove.contains(competence.getName()))
                        .collect(Collectors.toSet());
//                Set<Competence> filteredCompetences = oldValueUser.getCompetences().stream()
//                        .filter(competence -> !competence.getName().equals(((Competence) userUpdateRequest.getCompetencesToRemove().toArray()[0]).getName()))
//                        .collect(Collectors.toSet());
                userUpdateRequest.getCompetencesToAdd().addAll(filteredCompetences);
            }
            user.setCompetences(userUpdateRequest.getCompetencesToAdd().size() > 0 ? userUpdateRequest.getCompetencesToAdd() :
                    user.getCompetences());

            //Diplomes section
            if (userUpdateRequest.getDiplomesToAdd() != null && userUpdateRequest.getDiplomesToAdd().size() > 0) {
                userUpdateRequest.getDiplomesToAdd().addAll(oldValueUser.getDiplomes());
            }
            if (userUpdateRequest.getDiplomesToRemove() != null && userUpdateRequest.getDiplomesToRemove().size() > 0) {

//                Set<Diplome> filteredDiplome = oldValueUser.getDiplomes().stream()
//                        .filter(diplome -> !diplome.getTitle().equals(((Diplome) userUpdateRequest.getDiplomesToRemove().toArray()[0]).getTitle()))
//                        .collect(Collectors.toSet());

                Set<String> namesToRemove = userUpdateRequest.getDiplomesToRemove()
                        .stream()
                        .map(Diplome::getTitle)
                        .collect(Collectors.toSet());
                Set<Diplome> filteredDiplome = oldValueUser.getDiplomes().stream()
                        .filter(diplome -> !namesToRemove.contains(diplome.getLevel()))
                        .collect(Collectors.toSet());

                userUpdateRequest.getDiplomesToAdd().addAll(filteredDiplome);
            }
            user.setDiplomes(userUpdateRequest.getDiplomesToAdd());


            //Experience section
            if (userUpdateRequest.getExperienceToAdd() != null && userUpdateRequest.getExperienceToAdd().size() > 0) {
                userUpdateRequest.getExperienceToAdd().addAll(oldValueUser.getExperience());
            }
            user.setExperience(userUpdateRequest.getExperienceToAdd().size() > 0 ? userUpdateRequest.getExperienceToAdd() :
                    user.getExperience());

            return userDao.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with userName: " + userName));
    }
}
