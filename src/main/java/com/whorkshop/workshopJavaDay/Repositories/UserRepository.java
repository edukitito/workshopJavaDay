package com.whorkshop.workshopJavaDay.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whorkshop.workshopJavaDay.Entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
    Users findByEmail(String email);
}
