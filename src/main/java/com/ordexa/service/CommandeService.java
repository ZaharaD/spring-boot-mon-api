package com.ordexa.service;

import com.ordexa.model.Commande;
import com.ordexa.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> findAll() { return commandeRepository.findAll(); }
    public Page<Commande> findAll(Pageable pageable) { return commandeRepository.findAll(pageable); }
    public Optional<Commande> findById(Long id) { return commandeRepository.findById(id); }
    public Commande save(Commande commande) { return commandeRepository.save(commande); }
    public void delete(Long id) { commandeRepository.deleteById(id); }
} 