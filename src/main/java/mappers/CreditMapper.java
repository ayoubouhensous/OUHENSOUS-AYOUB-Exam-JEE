package mappers;

import dtos.CreditDTO;
import models.*;

import java.util.stream.Collectors;

public class CreditMapper {

    public static CreditDTO toDTO(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setStatut(credit.getStatut().name());
        dto.setMontant(credit.getMontant());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient().getId());

        if (credit.getRemboursements() != null) {
            dto.setRemboursements(
                    credit.getRemboursements().stream()
                            .map(RemboursementMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        // Champs spécifiques selon type
        if (credit instanceof CreditPersonnel personnel) {
            dto.setMotif(personnel.getMotif());
        } else if (credit instanceof CreditImmobilier immobilier) {
            dto.setTypeBien(immobilier.getTypeBien().name());
        } else if (credit instanceof CreditProfessionnel professionnel) {
            dto.setMotif(professionnel.getMotif());
            dto.setRaisonSociale(professionnel.getRaisonSociale());
        }

        return dto;
    }

    public static Credit fromDTO(CreditDTO dto) {
        Credit credit;

        // Choix du type
        if (dto.getRaisonSociale() != null) {
            CreditProfessionnel pro = new CreditProfessionnel();
            pro.setMotif(dto.getMotif());
            pro.setRaisonSociale(dto.getRaisonSociale());
            credit = pro;
        } else if (dto.getTypeBien() != null) {
            CreditImmobilier imm = new CreditImmobilier();
            imm.setTypeBien(TypeBien.valueOf(dto.getTypeBien()));
            credit = imm;
        } else if (dto.getMotif() != null) {
            CreditPersonnel per = new CreditPersonnel();
            per.setMotif(dto.getMotif());
            credit = per;
        } else {
            credit = new Credit(); // fallback
        }

        credit.setId(dto.getId());
        credit.setMontant(dto.getMontant());
        credit.setTauxInteret(dto.getTauxInteret());
        credit.setStatut(StatutCredit.valueOf(dto.getStatut()));

        // On ne set pas les remboursements ici pour éviter les boucles
        return credit;
    }
}
