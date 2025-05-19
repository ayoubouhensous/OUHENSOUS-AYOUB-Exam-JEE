package com.example.ouhensousayoubexamjeebackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_credit", discriminatorType = DiscriminatorType.STRING)
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutCredit statut;

    private double montant;

    @Temporal(TemporalType.DATE)
    private int dureeRemboursement;

    private double tauxInteret;

    private Date dateAcceptation;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;


}
