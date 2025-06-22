package com.ordexa.controller;

import com.ordexa.model.Commande;
import com.ordexa.model.Produit;
import com.ordexa.model.Client;
import com.ordexa.model.LigneCommande;
import com.ordexa.repository.CommandeRepository;
import com.ordexa.repository.ProduitRepository;
import com.ordexa.repository.ClientRepository;
import com.ordexa.repository.LigneCommandeRepository;
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
@RequestMapping("/api")
public class StatsController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        
        // Statistiques générales
        Map<String, Object> stats = new HashMap<>();
        List<Commande> commandes = commandeRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Produit> produits = produitRepository.findAll();
        
        stats.put("commandes", commandes.size());
        stats.put("clients", clients.size());
        stats.put("produits", produits.size());
        
        BigDecimal totalCA = commandes.stream()
                .map(cmd -> BigDecimal.valueOf(cmd.getTotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.put("chiffreAffaires", totalCA);
        
        dashboard.put("stats", stats);
        
        // Commandes récentes (5 dernières)
        List<Map<String, Object>> recentOrders = commandes.stream()
                .sorted((c1, c2) -> c2.getDate().compareTo(c1.getDate()))
                .limit(5)
                .map(cmd -> {
                    Map<String, Object> order = new HashMap<>();
                    order.put("id", cmd.getId());
                    order.put("client", cmd.getClient());
                    order.put("dateCommande", cmd.getDate());
                    order.put("total", cmd.getTotal());
                    order.put("statut", cmd.getStatut());
                    return order;
                })
                .collect(Collectors.toList());
        dashboard.put("recentOrders", recentOrders);
        
        // Produits populaires (basé sur les lignes de commande)
        Map<Long, Integer> productSales = new HashMap<>();
        List<LigneCommande> lignes = ligneCommandeRepository.findAll();
        
        for (LigneCommande ligne : lignes) {
            Long productId = ligne.getProduit().getId();
            productSales.put(productId, productSales.getOrDefault(productId, 0) + ligne.getQuantiteCommandee());
        }
        
        List<Map<String, Object>> popularProducts = produits.stream()
                .map(p -> {
                    Map<String, Object> product = new HashMap<>();
                    product.put("nom", p.getNom());
                    product.put("prix", p.getPrix());
                    product.put("vendus", productSales.getOrDefault(p.getId(), 0));
                    product.put("stock", p.getStock());
                    return product;
                })
                .sorted((p1, p2) -> ((Integer) p2.get("vendus")).compareTo((Integer) p1.get("vendus")))
                .limit(5)
                .collect(Collectors.toList());
        dashboard.put("popularProducts", popularProducts);
        
        // Ventes mensuelles (simulées pour l'instant)
        List<Integer> monthlySales = Arrays.asList(210000, 185000, 240000, 260000, 300000, 320000, 350000, 370000, 340000, 310000, 280000, 295000);
        dashboard.put("monthlySales", monthlySales);
        
        return dashboard;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Statistiques générales
        List<Commande> commandes = commandeRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Produit> produits = produitRepository.findAll();
        
        stats.put("totalOrders", commandes.size());
        stats.put("totalRevenue", commandes.stream()
                .mapToDouble(Commande::getTotal)
                .sum());
        stats.put("activeClients", clients.size());
        stats.put("soldProducts", produits.size());
        
        // Ventes quotidiennes (simulées)
        Map<String, Object> dailySales = new HashMap<>();
        dailySales.put("labels", Arrays.asList("Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"));
        dailySales.put("data", Arrays.asList(12000, 19000, 15000, 25000, 22000, 30000, 28000));
        stats.put("dailySales", dailySales);
        
        // Produits populaires
        Map<Long, Integer> productSales = new HashMap<>();
        List<LigneCommande> lignes = ligneCommandeRepository.findAll();
        
        for (LigneCommande ligne : lignes) {
            Long productId = ligne.getProduit().getId();
            productSales.put(productId, productSales.getOrDefault(productId, 0) + ligne.getQuantiteCommandee());
        }
        
        List<Map<String, Object>> topProducts = produits.stream()
                .map(p -> {
                    Map<String, Object> product = new HashMap<>();
                    product.put("nom", p.getNom());
                    product.put("ventes", productSales.getOrDefault(p.getId(), 0));
                    product.put("couleur", getRandomColor());
                    return product;
                })
                .sorted((p1, p2) -> ((Integer) p2.get("ventes")).compareTo((Integer) p1.get("ventes")))
                .limit(5)
                .collect(Collectors.toList());
        stats.put("topProducts", topProducts);
        
        // Ventes par catégorie (simulées)
        List<Map<String, Object>> categorySales = Arrays.asList(
            createCategorySale("Alimentation", 45, 1250000, 44),
            createCategorySale("Électronique", 32, 890000, 31),
            createCategorySale("Vêtements", 28, 420000, 15),
            createCategorySale("Accessoires", 15, 180000, 6),
            createCategorySale("Autres", 8, 107500, 4)
        );
        stats.put("categorySales", categorySales);
        
        return stats;
    }

    private Map<String, Object> createCategorySale(String categorie, int ventes, int revenu, int pourcentage) {
        Map<String, Object> category = new HashMap<>();
        category.put("categorie", categorie);
        category.put("ventes", ventes);
        category.put("revenu", revenu);
        category.put("pourcentage", pourcentage);
        return category;
    }

    private String getRandomColor() {
        String[] colors = {"#4f46e5", "#16a34a", "#f59e42", "#dc2626", "#0ea5e9"};
        return colors[new Random().nextInt(colors.length)];
    }

    @GetMapping("/stats/chiffre-affaires")
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

    @GetMapping("/stats/ventes-par-jour")
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

    @GetMapping("/stats/top-produits")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, Object>> getTopProduits() {
        List<Produit> produits = produitRepository.findAll();
        List<Map<String, Object>> top = new ArrayList<>();
        
        Map<Long, Integer> productSales = new HashMap<>();
        List<LigneCommande> lignes = ligneCommandeRepository.findAll();
        
        for (LigneCommande ligne : lignes) {
            Long productId = ligne.getProduit().getId();
            productSales.put(productId, productSales.getOrDefault(productId, 0) + ligne.getQuantiteCommandee());
        }
        
        for (Produit p : produits) {
            Map<String, Object> map = new HashMap<>();
            map.put("produit", p.getNom());
            map.put("ventes", productSales.getOrDefault(p.getId(), 0));
            top.add(map);
        }
        top.sort((a, b) -> ((Integer) b.get("ventes")).compareTo((Integer) a.get("ventes")));
        return top.subList(0, Math.min(5, top.size()));
    }
} 