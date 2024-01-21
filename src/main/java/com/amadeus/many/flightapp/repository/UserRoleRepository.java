package com.amadeus.many.flightapp.repository;

import com.amadeus.many.flightapp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT ur FROM UserRole ur WHERE ur.authority = :authority")
    Optional<UserRole> findByAuthority(String authority);
}
