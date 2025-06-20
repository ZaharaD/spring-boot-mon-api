package com.ordexa.service;

import com.ordexa.model.LigneCommande;
import com.ordexa.repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LigneCommandeService {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<LigneCommande> findAll() { return ligneCommandeRepository.findAll(); }
    public Optional<LigneCommande> findById(Long id) { return ligneCommandeRepository.findById(id); }
    public LigneCommande save(LigneCommande ligne) { return ligneCommandeRepository.save(ligne); }
    public void delete(Long id) { ligneCommandeRepository.deleteById(id); }
} 