package it.objectmethod.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.demo.spring.models.UserObject;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Long> {

    // Login query using MD5 hash
    @Query(value = "SELECT * FROM users WHERE username = :username AND password = MD5(:password)", nativeQuery = true)
    UserObject login(@Param("username") String username, @Param("password") String password);

    // Find user by username (for checking existing user during registration)
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    UserObject findByUsername(@Param("username") String username);
}
