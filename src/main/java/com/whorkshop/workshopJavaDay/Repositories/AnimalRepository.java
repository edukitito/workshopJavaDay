package com.whorkshop.workshopJavaDay.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whorkshop.workshopJavaDay.Entities.Animais;

@Repository
public interface AnimalRepository extends JpaRepository<Animais, Integer>{
    List<Animais> findByAtivo(Boolean ativo);
    List<Animais> findByTipoAndAtivo(String tipo, Boolean ativo);

    @Query(value="Select a.id, a.ativo, a.descricao, a.idade, a.imagem, a.nome, a.proprietario_id, a.proprietario_tipo, a.raca, a.sexo, a.tipo from animais a inner join ongs o on a.proprietario_id=o.ongid where o.ongid = :ongid and a.proprietario_tipo = :proprietarioTipo and a.ativo = 1", nativeQuery=true)
    List<Animais> findByOng(@Param("ongid") Integer proprietarioId, @Param("proprietarioTipo") String proprietarioTipo);

    @Query(value="Select a.id, a.ativo, a.descricao, a.idade, a.imagem, a.nome, a.proprietario_id, a.proprietario_tipo, a.raca, a.sexo, a.tipo from animais a inner join ongs o on a.proprietario_id=o.ongid where o.cidade = :cidade and a.ativo = 1",nativeQuery=true)
    List<Animais> findAnimaisByCidade(@Param("cidade") String cidade);

    @Query(value="Select a.id, a.ativo, a.descricao, a.idade, a.imagem, a.nome, a.proprietario_id, a.proprietario_tipo, a.raca, a.sexo, a.tipo from animais a inner join ongs o on a.proprietario_id=o.ongid where o.cidade = :estado and a.ativo = 1",nativeQuery=true)
    List<Animais> findAnimaisByEstado(@Param("estado") String estado);

    @Query(value="Select a.id, a.ativo, a.descricao, a.idade, a.imagem, a.nome, a.proprietario_id, a.proprietario_tipo, a.raca, a.sexo, a.tipo from animais a inner join ongs o on a.proprietario_id=o.ongid where o.cidade = :cidade and a.tipo = :tipo and a.ativo = 1",nativeQuery=true)
    List<Animais> findAnimaisByCidadeTipo(@Param("cidade") String cidade, @Param("tipo") String tipo);

    @Query(value="Select a.id, a.ativo, a.descricao, a.idade, a.imagem, a.nome, a.proprietario_id, a.proprietario_tipo, a.raca, a.sexo, a.tipo from animais a inner join ongs o on a.proprietario_id=o.ongid where o.estado = :estado and a.tipo = :tipo and a.ativo = 1",nativeQuery=true)
    List<Animais> findAnimaisByEstadoTipo(@Param("estado") String cidade, @Param("tipo") String tipo);
}
