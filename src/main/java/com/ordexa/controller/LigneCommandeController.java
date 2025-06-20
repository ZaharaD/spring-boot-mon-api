package com.ordexa.controller;

import com.ordexa.model.LigneCommande;
import com.ordexa.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lignecommandes")
public class LigneCommandeController {
    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<LigneCommande> getAllLigneCommandes() {
        return ligneCommandeService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LigneCommande> getLigneCommandeById(@PathVariable Long id) {
        Optional<LigneCommande> ligne = ligneCommandeService.findById(id);
        return ligne.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public LigneCommande createLigneCommande(@RequestBody LigneCommande ligne) {
        return ligneCommandeService.save(ligne);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LigneCommande> updateLigneCommande(@PathVariable Long id, @RequestBody LigneCommande ligne) {
        if (!ligneCommandeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ligne.setId(id);
        return ResponseEntity.ok(ligneCommandeService.save(ligne));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLigneCommande(@PathVariable Long id) {
        if (!ligneCommandeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ligneCommandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 