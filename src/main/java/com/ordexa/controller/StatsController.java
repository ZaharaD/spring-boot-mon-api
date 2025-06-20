package com.ordexa.controller;

import com.ordexa.model.Commande;
import com.ordexa.model.Produit;
import com.ordexa.repository.CommandeRepository;
import com.ordexa.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/chiffre-affaires")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> getChiffreAffaires() {
        BigDecimal ca = commandeRepository.findAll().stream()
                .map(Commande::getTotal)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> res = new HashMap<>();
        res.put("chiffreAffaires", ca);
        return res;
    }

    @GetMapping("/ventes-par-jour")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<LocalDate, BigDecimal> getVentesParJour() {
        return commandeRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Commande::getDate,
                        Collectors.mapping(
                            cmd -> BigDecimal.valueOf(cmd.getTotal()),
                            Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
    }

    @GetMapping("/top-produits")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, Object>> getTopProduits() {
        // Simulé: à adapter selon structure LigneCommande
        List<Produit> produits = produitRepository.findAll();
        List<Map<String, Object>> top = new ArrayList<>();
        for (Produit p : produits) {
            Map<String, Object> map = new HashMap<>();
            map.put("produit", p.getNom());
            map.put("ventes", new Random().nextInt(100)); // à remplacer par vrai calcul
            top.add(map);
        }
        top.sort((a, b) -> ((Integer) b.get("ventes")).compareTo((Integer) a.get("ventes")));
        return top.subList(0, Math.min(5, top.size()));
    }
} 