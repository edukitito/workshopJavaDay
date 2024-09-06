package com.whorkshop.workshopJavaDay.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whorkshop.workshopJavaDay.Entities.Ongs;

@Repository
public interface OngRepository extends JpaRepository<Ongs, Integer>{
    Boolean existsByCnpj(String cnpj);
    Boolean existsByEmail(String email);
    Ongs findByCnpj(String cnpj);
}
