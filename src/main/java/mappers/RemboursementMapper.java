package mappers;

import dtos.RemboursementDTO;
import models.Remboursement;
import models.TypeRemboursement;

public class RemboursementMapper {

    public static RemboursementDTO toDTO(Remboursement r) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(r.getId());
        dto.setMontant(r.getMontant());
        dto.setType(r.getType().name());
        dto.setCreditId(r.getCredit().getId());
        return dto;
    }

    public static Remboursement fromDTO(RemboursementDTO dto) {
        Remboursement r = new Remboursement();
        r.setId(dto.getId());
        r.setMontant(dto.getMontant());
        r.setType(TypeRemboursement.valueOf(dto.getType()));


        return r;
    }
}
