package com.whorkshop.workshopJavaDay.Controllers;

import java.util.List;
import java.util.Optional;

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

import com.whorkshop.workshopJavaDay.Entities.Adocoes;
import com.whorkshop.workshopJavaDay.Services.AdocaoService;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    @PostMapping
    public ResponseEntity<Adocoes> createAdocao(@RequestBody Adocoes adocao) {
        Adocoes createdAdocao = service.saveAdocao(adocao);
        return new ResponseEntity<>(createdAdocao, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Adocoes>> getAllAdocoes() {
        List<Adocoes> adocoes = service.getAllAdocoes();
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adocoes> getAdocaoById(@PathVariable Integer id) {
        Optional<Adocoes> adocao = service.getAdocaoById(id);
        if (adocao.isPresent()) {
            return new ResponseEntity<>(adocao.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Adocoes> updateAdocao(@PathVariable Integer id, @RequestBody Adocoes adocao) {
        adocao.setId(id);
        Adocoes updatedAdocao = service.updateAdocao(adocao);
        return new ResponseEntity<>(updatedAdocao, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateAdocaoStatus(@PathVariable Integer id, @RequestBody Adocoes adocao) {
        service.updateAdocaoStatus(id, adocao);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/etapa")
    public ResponseEntity<Void> updateAdocaoEtapa(@PathVariable Integer id, @RequestBody Adocoes adocao) {
        service.updateAdocaoEtapa(id, adocao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ong/{ongId}")
    public ResponseEntity<List<Adocoes>> getAdocoesByOngId(@PathVariable Integer ongId) {
        List<Adocoes> adocoes = service.getAdocoesByOngId(ongId);
        return ResponseEntity.ok().body(adocoes);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Adocoes>> getAdocoesByUserId(@PathVariable Integer userId) {
        List<Adocoes> adocoes = service.getAdocoesByUserId(userId);
        return ResponseEntity.ok().body(adocoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdocao(@PathVariable Integer id) {
        service.deleteAdocao(id);
        return ResponseEntity.noContent().build();
    }
}
