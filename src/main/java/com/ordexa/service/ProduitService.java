package com.ordexa.service;

import com.ordexa.model.Produit;
import com.ordexa.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> findAll() { return produitRepository.findAll(); }
    public Page<Produit> findAll(Pageable pageable) { return produitRepository.findAll(pageable); }
    public Optional<Produit> findById(Long id) { return produitRepository.findById(id); }
    public Produit save(Produit produit) { return produitRepository.save(produit); }
    public void delete(Long id) { produitRepository.deleteById(id); }
} 