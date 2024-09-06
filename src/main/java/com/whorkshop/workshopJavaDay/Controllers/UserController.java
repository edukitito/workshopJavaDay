package com.whorkshop.workshopJavaDay.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whorkshop.workshopJavaDay.Entities.Users;
import com.whorkshop.workshopJavaDay.Entities.DTOs.LoginDTO;
import com.whorkshop.workshopJavaDay.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        Users user = service.findById(id);
        if (user!=null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Users> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(service.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users user){
        Users users = service.create(user);
        if (users!=null) {
            return new ResponseEntity<>(users,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
        String credencial = service.login(login);
        if (credencial.equals("Aprovado")) {
            return ResponseEntity.ok().body(credencial);
        } else if(credencial.equals("Senha Incorreta")){
            return new ResponseEntity<>(credencial, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(credencial, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Integer id, @RequestBody Users usuario){
        return ResponseEntity.ok().body(service.update(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
