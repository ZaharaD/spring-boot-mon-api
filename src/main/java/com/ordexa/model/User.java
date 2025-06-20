package com.ordexa.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String lieuDeNaissance;
    private String email;
    private String telephone;
    private String adresse;
    private String motDePasse;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, CLIENT, VISITEUR
    }

    public String getPassword() {
        return this.motDePasse;
    }
    public void setPassword(String password) {
        this.motDePasse = password;
    }
} 