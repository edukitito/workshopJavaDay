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

import com.whorkshop.workshopJavaDay.Entities.Ongs;
import com.whorkshop.workshopJavaDay.Entities.DTOs.LoginDTO;
import com.whorkshop.workshopJavaDay.Services.OngService;

@RestController
@RequestMapping("/ongs")
public class OngController {
    @Autowired
    private OngService service;

    @GetMapping
    public ResponseEntity<List<Ongs>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ongs> findById(@PathVariable Integer id){
        Ongs ong = service.findById(id);
        if (ong!=null) {
            return ResponseEntity.ok().body(ong);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Ongs> findByCnpj(@PathVariable String cnpj){
        return ResponseEntity.ok().body(service.findbyCnpj(cnpj));
    }

    @PostMapping
    public ResponseEntity<Ongs> create(@RequestBody Ongs ong){
        return new ResponseEntity<>(service.create(ong), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ongs> update(@PathVariable Integer id, @RequestBody Ongs ong){
        return ResponseEntity.ok().body(service.Update(id, ong));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
        String response = service.login(login);
        if (response.equals("Aprovado")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.equals("Senha Incorreta")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
