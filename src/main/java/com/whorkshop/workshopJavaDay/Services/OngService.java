package com.whorkshop.workshopJavaDay.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whorkshop.workshopJavaDay.Entities.Ongs;
import com.whorkshop.workshopJavaDay.Entities.DTOs.LoginDTO;
import com.whorkshop.workshopJavaDay.Repositories.OngRepository;

@Service
public class OngService {
    @Autowired
    private OngRepository repository;

    public List<Ongs> findAll(){
        return repository.findAll();
    }

    public Ongs findById(Integer id){
        if (repository.existsById(id)) {
            return repository.findById(id).get();
        }
        return null;
    }

    public Ongs create(Ongs ong){
        if (repository.existsByCnpj(ong.getCnpj()) || repository.existsByEmail(ong.getEmail())) {
            return null;
        }
        return repository.save(ong);
    }

    public Ongs Update(Integer id, Ongs ong){
        ong.setOngid(id);
        return repository.save(ong);
    }

    public void delete(Integer id){
        Optional<Ongs> ongs = repository.findById(id);
        if(ongs.isPresent()){
            Ongs ong = ongs.get();
            ong.setAtivo(false);
            ong.setCnpj(ong.getCnpj()+"deleted"+ong.getOngid().toString());
            ong.setNome(ong.getNome()+"deleted"+ong.getOngid().toString());
            ong.setEmail(ong.getEmail()+"deleted"+ong.getOngid().toString());
            ong.setTelefone(ong.getTelefone()+"deleted"+ong.getOngid().toString());
            ong.setPix(ong.getPix()+"deleted"+ong.getOngid().toString());
            repository.save(ong);
        }
    }

    public Ongs findbyCnpj(String cnpj){
        return repository.findByCnpj(cnpj);
    }

    public String login(LoginDTO login){
        if (repository.existsByCnpj(login.getEmail())) {
            if (repository.findByCnpj(login.getEmail()).getSenha().equals(login.getSenha())) {
                return "Aprovado";
            }
            return "Senha Incorreta";
        }
        return "Usuário não encontrado";
    }
}
