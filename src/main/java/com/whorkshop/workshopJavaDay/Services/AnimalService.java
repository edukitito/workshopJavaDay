package com.whorkshop.workshopJavaDay.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whorkshop.workshopJavaDay.Entities.Animais;
import com.whorkshop.workshopJavaDay.Entities.DTOs.AnimalOngDTO;
import com.whorkshop.workshopJavaDay.Entities.Ongs;
import com.whorkshop.workshopJavaDay.Repositories.AnimalRepository;
import com.whorkshop.workshopJavaDay.Repositories.OngRepository;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository repository;

    @Autowired
    private OngRepository ongRepository;

    public List<Animais> findAll(){
        return repository.findAll();
    }

    public Animais findById(Integer id){
        return repository.findById(id).get();
    }

    public void delete(Integer id){
        Optional<Animais> animal = repository.findById(id);
        if(animal.isPresent()){
            Animais update = animal.get();
            update.setAtivo(false);
            repository.save(update);
        }
    }

    public Animais create(String nome, String raca, String sexo, String descricao, String tipo, int idade, Integer proprietarioId, MultipartFile imagem){
        Optional<Ongs> ong = ongRepository.findById(proprietarioId);
        if (ong.isPresent()) {
            Animais animal = new Animais();
            animal.setNome(nome);
            animal.setRaca(raca);
            animal.setSexo(sexo);
            animal.setDescricao(descricao);
            animal.setIdade(idade);
            animal.setTipo(tipo);
            animal.setProprietarioTipo("ong");
            animal.setProprietarioId(ong.get().getOngid());
            animal.setAtivo(true);
            try {
                animal.setImagem(imagem.getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return repository.save(animal);
        }
        return null;
    }

    public Animais update(Integer id, String nome, String raca, String sexo, String descricao, String tipo, int idade, Integer proprietarioId, MultipartFile imagem){
        Optional<Animais> animais = repository.findById(id);
        if (animais.isPresent()) {
            Animais animal = animais.get();
            animal.setNome(nome);
            animal.setRaca(raca);
            animal.setSexo(sexo);
            animal.setDescricao(descricao);
            animal.setIdade(idade);
            animal.setTipo(tipo);
            animal.setAtivo(true);
            if (imagem!=null) {
                try {
                    animal.setImagem(imagem.getBytes());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            return repository.save(animal);
        }
        return null;
    }

    public List<Animais> findByOng(Integer id){
        return repository.findByOng(id, "ong");
    }

    public List<Animais> searchAnimals(String tipo, String cidade, String estado) {
        if (tipo == null && cidade == null && estado == null) {
            return repository.findByAtivo(true);
        } else if (tipo != null && (cidade == null || cidade.equals("")) && (estado == null || estado.equals(""))) {
            return repository.findByTipoAndAtivo(tipo, true);
        } else if (tipo == null && cidade != null && !cidade.equals("") && (estado == null || estado.equals(""))) {
            return repository.findAnimaisByCidade(cidade);
        } else if (tipo == null && (cidade == null || cidade.equals("")) && estado != null && !estado.equals("")) {
            return repository.findAnimaisByEstado(estado);
        } else if (tipo != null && cidade != null && !cidade.equals("") && (estado == null || estado.equals(""))) {
            return repository.findAnimaisByCidadeTipo(cidade, tipo);
        } else if (tipo != null && (cidade == null || cidade.equals("")) && estado != null && !estado.equals("")) {
            return repository.findAnimaisByEstadoTipo(estado, tipo);
        } else {
            return repository.findByAtivo(true);
        }
    }

    public Optional<AnimalOngDTO> getAnimalOngDetails(Integer id) {
        Optional<Animais> animal = repository.findById(id);
        if (animal.isPresent()) {
            Animais a = animal.get();
            Optional<Ongs> ong = ongRepository.findById(a.getProprietarioId());
            if (ong.isPresent()) {
                Ongs o = ong.get();
                AnimalOngDTO dto = new AnimalOngDTO();
                dto.setAnimalId(a.getId());
                dto.setAnimalNome(a.getNome());
                dto.setAnimalRaca(a.getRaca());
                dto.setAnimalSexo(a.getSexo());
                dto.setAnimalDescricao(a.getDescricao());
                dto.setAnimalIdade(a.getIdade());
                dto.setAnimalTipo(a.getTipo());
                dto.setAnimalImagem(a.getImagem());
                dto.setOngId(o.getOngid());
                dto.setOngNome(o.getNome());
                dto.setOngDescricao(o.getDescricao());
                dto.setOngEmail(o.getEmail());
                dto.setOngTelefone(o.getTelefone());
                dto.setOngEndereco(o.getEndereco());
                dto.setOngCidade(o.getCidade());
                dto.setOngEstado(o.getEstado());
                dto.setOngCnpj(o.getCnpj());
                dto.setOngPix(o.getPix());
                return Optional.of(dto);
            }
        }
        return null;
    }
}
