package com.ordexa.controller;

import com.ordexa.model.Commande;
import com.ordexa.service.CommandeService;
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
@RequestMapping("/api/commandes")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Commande> getAllCommandes() {
        return commandeService.findAll();
    }

    @GetMapping("/page")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Commande> getCommandesPage(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commandeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.findById(id);
        return commande.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        if (!commandeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commande.setId(id);
        return ResponseEntity.ok(commandeService.save(commande));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        if (!commandeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 