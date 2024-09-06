package com.whorkshop.workshopJavaDay.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whorkshop.workshopJavaDay.Entities.Adocoes;
import com.whorkshop.workshopJavaDay.Entities.Enums.EtapaAdocao;
import com.whorkshop.workshopJavaDay.Entities.Enums.StatusAdocao;
import com.whorkshop.workshopJavaDay.Entities.Ongs;
import com.whorkshop.workshopJavaDay.Entities.Users;
import com.whorkshop.workshopJavaDay.Repositories.AdocaoRepository;
import com.whorkshop.workshopJavaDay.Repositories.OngRepository;
import com.whorkshop.workshopJavaDay.Repositories.UserRepository;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired 
    private OngRepository ongRepository;

    @Autowired
    private UserRepository userRepository;


    public Adocoes saveAdocao(Adocoes adocao) {
        adocao.setData(new Date());
        adocao.setEtapaAdocao(EtapaAdocao.INICIO);
        adocao.setStatusAdocao(StatusAdocao.PENDENTE);
        return repository.save(adocao);
    }

    public List<Adocoes> getAllAdocoes() {
        return repository.findAll();
    }

    public List<Adocoes> getAdocoesByUserId(Integer userId) {
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            return repository.findByUsers_id(userId);
        }
        return null;
    }

    public List<Adocoes> getAdocoesByOngId(Integer ongId) {
        Optional<Ongs> ong = ongRepository.findById(ongId);
        if(ong.isPresent()){
            System.out.println(ongId);
            System.out.println(repository.findByOng_Ongid(ongId));
            return repository.findByOng_Ongid(ongId);
        }
        return null;
    }

    public Optional<Adocoes> getAdocaoById(Integer id) {
        return repository.findById(id);
    }

    public Adocoes updateAdocao(Adocoes adocao) {
        return repository.save(adocao);
    }

    public void updateAdocaoStatus(Integer id, Adocoes update) {
        Optional<Adocoes> adocoes = repository.findById(id);
        if (adocoes.isPresent()) {
            Adocoes adocao = adocoes.get();
            adocao.setStatusAdocao(update.getStatusAdocao());
            repository.save(adocao);
        }
    }

    public void updateAdocaoEtapa(Integer id, Adocoes update) {
        Optional<Adocoes> adocoes = repository.findById(id);
        if (adocoes.isPresent()) {
            Adocoes adocao = adocoes.get();
            adocao.setEtapaAdocao(update.getEtapaAdocao());
            repository.save(adocao);
        }
    }

    public void deleteAdocao(Integer id) {
        repository.deleteById(id);
    }
}
