package com.whorkshop.workshopJavaDay.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whorkshop.workshopJavaDay.Entities.Adocoes;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocoes, Integer>{
    List<Adocoes> findByUsers_id(Integer id);
    List<Adocoes> findByOng_Ongid(Integer id);
}
