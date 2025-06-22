package com.ordexa.config;

import com.ordexa.model.Client;
import com.ordexa.model.Produit;
import com.ordexa.model.User;
import com.ordexa.repository.ClientRepository;
import com.ordexa.repository.ProduitRepository;
import com.ordexa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DemoDataConfig implements CommandLineRunner {

    private final ProduitRepository produitRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public DemoDataConfig(ProduitRepository produitRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.produitRepository = produitRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Charger les produits de démo
        if (produitRepository.count() == 0) {
            Produit p1 = Produit.builder().nom("Ciment 50kg").categorie("Construction").prix(5000).stock(120).statut("disponible").fournisseur("CIMAF").description("Sac de ciment 50kg pour construction.").build();
            Produit p2 = Produit.builder().nom("Riz parfumé 25kg").categorie("Alimentation").prix(15000).stock(30).statut("limité").fournisseur("Rizerie du Nord").description("Riz parfumé 25kg, origine Asie.").build();
            Produit p3 = Produit.builder().nom("Savon antiseptique").categorie("Hygiène").prix(800).stock(0).statut("rupture").fournisseur("Savonnerie Africaine").description("Savon pour mains et corps.").build();
            Produit p4 = Produit.builder().nom("T-shirt coton").categorie("Textile").prix(3500).stock(60).statut("disponible").fournisseur("Textile Plus").description("T-shirt 100% coton, plusieurs tailles.").build();
            Produit p5 = Produit.builder().nom("Ordinateur portable 15\"").categorie("Électronique").prix(350000).stock(8).statut("limité").fournisseur("TechStore").description("Laptop 15\" SSD 512Go, 8Go RAM.").build();
            Produit p6 = Produit.builder().nom("Fer à béton 12mm").categorie("Construction").prix(4500).stock(250).statut("disponible").fournisseur("CIMAF").description("Barre de fer pour la construction.").build();
            Produit p7 = Produit.builder().nom("Peinture blanche 15L").categorie("Construction").prix(25000).stock(25).statut("limité").fournisseur("Coloria").description("Pot de peinture pour intérieur/extérieur.").build();
            Produit p8 = Produit.builder().nom("Carton de Tomates").categorie("Alimentation").prix(7000).stock(50).statut("disponible").fournisseur("AgriMali").description("Carton de 20kg de tomates fraîches.").build();
            Produit p9 = Produit.builder().nom("Bidon d'huile 5L").categorie("Alimentation").prix(9000).stock(0).statut("rupture").fournisseur("Huilerie du Sud").description("Huile végétale pour cuisson.").build();
            Produit p10 = Produit.builder().nom("Clé USB 32Go").categorie("Électronique").prix(6000).stock(150).statut("disponible").fournisseur("TechStore").description("Clé de stockage USB 3.0.").build();
            produitRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        }

        // Charger l'utilisateur admin de démo
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .prenom("Admin")
                    .nom("Ordexa")
                    .email("admin@ordexa.com")
                    .motDePasse(passwordEncoder.encode("admin"))
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
        
        // Charger les clients de démo
        if (clientRepository.count() == 0) {
            Client c1 = Client.builder().prenom("Mohamed").nom("Kone").email("mohamed.kone@email.com").tel("0700000001").inscription(LocalDate.parse("2024-01-10")).build();
            Client c2 = Client.builder().prenom("Awa").nom("Traore").email("awa.traore@email.com").tel("0700000002").inscription(LocalDate.parse("2024-02-15")).build();
            Client c3 = Client.builder().prenom("Issa").nom("Coulibaly").email("issa.coulibaly@email.com").tel("0700000003").inscription(LocalDate.parse("2024-03-20")).build();
            Client c4 = Client.builder().prenom("Fatim").nom("Diarra").email("fatim.diarra@email.com").tel("0700000004").inscription(LocalDate.parse("2024-04-01")).build();
            Client c5 = Client.builder().prenom("Moussa").nom("Sissoko").email("moussa.sissoko@email.com").tel("0700000005").inscription(LocalDate.parse("2024-04-05")).build();
            Client c6 = Client.builder().prenom("Ousmane").nom("Keita").email("ousmane.keita@email.com").tel("0700000006").inscription(LocalDate.parse("2024-05-10")).build();
            Client c7 = Client.builder().prenom("Aicha").nom("Ballo").email("aicha.ballo@email.com").tel("0700000007").inscription(LocalDate.parse("2024-05-12")).build();
            Client c8 = Client.builder().prenom("Mariam").nom("Sangaré").email("mariam.sangare@email.com").tel("0700000008").inscription(LocalDate.parse("2024-05-20")).build();
            Client c9 = Client.builder().prenom("Boubacar").nom("Diallo").email("boubacar.diallo@email.com").tel("0700000009").inscription(LocalDate.parse("2024-06-02")).build();
            Client c10 = Client.builder().prenom("Sekou").nom("Doumbia").email("sekou.doumbia@email.com").tel("0700000010").inscription(LocalDate.parse("2024-06-11")).build();
            clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
        }
    }
} 