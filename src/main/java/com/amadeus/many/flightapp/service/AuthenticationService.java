package com.amadeus.many.flightapp.service;

import com.amadeus.many.flightapp.entity.Member;
import com.amadeus.many.flightapp.entity.UserRole;
import com.amadeus.many.flightapp.repository.MemberRepository;
import com.amadeus.many.flightapp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password) {
        Optional<Member> foundMember = memberRepository.findMemberByEmail(email);
        if (foundMember.isPresent()) {
            throw new RuntimeException("User with given email already exists, please login: " + email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        // Check if the "USER" role exists
        Optional<UserRole> userRoleOptional = roleRepository.findByAuthority("USER");
        UserRole userRole = userRoleOptional.orElseThrow(() -> new RuntimeException("Role with authority 'USER' not found"));

        Set<UserRole> userRoleList = new HashSet<>();
        userRoleList.add(userRole);

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(userRoleList);
        return memberRepository.save(member);
    }
}