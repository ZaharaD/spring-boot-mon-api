package com.ordexa.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToMany
    private List<Produit> produits;
    private int total;
    private LocalDate date;
    private String statut;
    private String adresse;
    private String paiement;
} 