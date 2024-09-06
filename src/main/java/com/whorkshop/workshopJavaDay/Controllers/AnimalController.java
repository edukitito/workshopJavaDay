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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.whorkshop.workshopJavaDay.Entities.Animais;
import com.whorkshop.workshopJavaDay.Entities.DTOs.AnimalOngDTO;
import com.whorkshop.workshopJavaDay.Services.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalController {
    @Autowired
    private AnimalService service;

    @GetMapping
    public ResponseEntity<List<Animais>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animais> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/A/{id}")
    public ResponseEntity<List<Animais>> findByOng(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findByOng(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Animais> create(@RequestParam("nome") String nome,
                                        @RequestParam("raca") String raca,
                                        @RequestParam("sexo") String sexo,
                                        @RequestParam("descricao") String descricao,
                                        @RequestParam("idade") int idade,
                                        @RequestParam("tipo") String tipo,
                                        @RequestParam("proprietarioId") Integer proprietarioId,
                                        @RequestParam("imagem") MultipartFile imagem){
        Animais animais = service.create(nome, raca, sexo, descricao, tipo, idade, proprietarioId, imagem);
        if(animais!=null){
            return new ResponseEntity<>(animais,HttpStatus.CREATED);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animais> update(@PathVariable Integer id, @RequestParam("nome") String nome,
                                            @RequestParam("raca") String raca,
                                            @RequestParam("sexo") String sexo,
                                            @RequestParam("descricao") String descricao,
                                            @RequestParam("idade") int idade,
                                            @RequestParam("tipo") String tipo,
                                            @RequestParam("proprietarioId") Integer proprietarioId,
                                            @RequestParam("imagem") MultipartFile imagem){
        Animais animal = service.update(id, nome, raca, sexo, descricao, tipo, idade, proprietarioId, imagem);
        if (animal!=null) {
            return ResponseEntity.ok().body(animal);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Animais>> searchAnimals(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "estado", required = false) String estado) {
        List<Animais> animais = service.searchAnimals(tipo, cidade, estado);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<AnimalOngDTO> getAnimalOngDetails(@PathVariable Integer id) {
        Optional<AnimalOngDTO> dto = service.getAnimalOngDetails(id);
        return dto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
