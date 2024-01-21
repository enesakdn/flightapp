package com.amadeus.many.flightapp.repository;

import com.amadeus.many.flightapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findMemberByEmail(String email);
}