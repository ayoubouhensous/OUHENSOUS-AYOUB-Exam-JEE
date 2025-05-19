package service;

import dtos.RemboursementDTO;

import java.util.List;

public interface RemboursementService {

    RemboursementDTO createRemboursement(RemboursementDTO dto);
    RemboursementDTO updateRemboursement(Long id, RemboursementDTO dto);
    void deleteRemboursement(Long id);
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);

}
