package com.ordexa.controller;

import com.ordexa.model.Produit;
import com.ordexa.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Produit> getAllProduits() {
        return produitService.findAll();
    }

    @GetMapping("/page")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Produit> getProduitsPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.findById(id);
        return produit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.save(produit);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        if (!produitService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produit.setId(id);
        return ResponseEntity.ok(produitService.save(produit));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (!produitService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 