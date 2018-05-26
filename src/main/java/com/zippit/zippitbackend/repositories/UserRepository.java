package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.entities.UserType;
import com.zippit.zippitbackend.entities.StandardDivision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 21-Jun-17.
 */

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findFirstByContactNumber(String contactNumber);

    User findFirstByEmail(String email);

    User findFirstByUsername(String username);

    User findFirstByUsernameAndPassword(String username, String password);

    User findByEmailAndPasswordAndUserType(String email, String password, UserType userType);

    @Query(value = "select u from User u where u.email = :email and u.password = :password and u.userType in :userTypes")
    User findByEmailAndPasswordAndUserType(@Param("email") String email, @Param("password") String password, @Param("userTypes") List<UserType> userTypes);

    @Query(value = "select count(*) from User u where u.standardDivision in :standardDivisions and u.admin = false")
    Integer findCountByStandardDivisions(@Param("standardDivisions") List<StandardDivision> standardDivisions);

    @Query(value = "select u.fcmToken from User u where u.standardDivision in :standardDivisions")
    List<String> fetchFcmTokensByStandardDivisions(@Param("standardDivisions") List<StandardDivision> standardDivisions);
}
