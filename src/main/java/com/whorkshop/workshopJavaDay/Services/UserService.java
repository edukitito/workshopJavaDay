package com.whorkshop.workshopJavaDay.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whorkshop.workshopJavaDay.Entities.Users;
import com.whorkshop.workshopJavaDay.Entities.DTOs.LoginDTO;
import com.whorkshop.workshopJavaDay.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<Users> findAll(){
        return repository.findAll();
    }
    
    public Users findById(Integer id){
        Optional<Users> users = repository.findById(id);
        if (users.isPresent()) {
            return users.get();
        }
        return null;
    }

    public Users create(Users user){
        if (repository.existsByCpf(user.getCpf()) || repository.existsByEmail(user.getEmail())) {
            return null;
        }
        return repository.save(user);
    }

    public String login(LoginDTO login){
        Users users = repository.findByEmail(login.getEmail());
        if (users!= null) {
            if (users.getSenha().equals(login.getSenha())) {
                return "Aprovado";
            }
            return "Senha Incorreta";
        }
        return "Usuário não encontrado";
    }

    public Users findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Users update(Integer id, Users usuario){
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void delete(Integer id){
        Optional<Users> update = repository.findById(id);
        if(update.isPresent()){
            Users usuario = update.get();
            usuario.setEmail(usuario.getEmail()+"deleted"+usuario.getId().toString());
            usuario.setTelefone(usuario.getTelefone()+usuario.getId().toString());
            usuario.setCpf(usuario.getCpf()+"deleted"+usuario.getId());
            usuario.setNickname(usuario.getNickname()+"deleted"+usuario.getId());
            usuario.setAtivo(false);
            repository.save(usuario);
        }
    }
}
