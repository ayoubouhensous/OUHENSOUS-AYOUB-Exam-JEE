package dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class CreditDTO {

    private Long id;
    private String statut;
    private double montant;
    private double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;

    // Spécificités des sous-types de crédit
    private String motif; // Pour personnel et professionnel
    private String raisonSociale; // Pour professionnel
    private String typeBien; // Pour immobilier

}
