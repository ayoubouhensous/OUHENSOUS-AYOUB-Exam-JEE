package service;

import dtos.RemboursementDTO;
import mappers.RemboursementMapper;
import models.Credit;
import models.Remboursement;
import models.TypeRemboursement;
import repository.CreditRepository;
import repository.RemboursementRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RemboursementServiceImpl implements RemboursementService {


    private RemboursementRepository remboursementRepository;
    private CreditRepository creditRepository;
    public RemboursementServiceImpl(RemboursementRepository remboursementRepository, CreditRepository creditRepository) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
    }

    @Override
    public RemboursementDTO createRemboursement(RemboursementDTO dto) {
        Credit credit = creditRepository.findById(dto.getCreditId())
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        Remboursement remboursement = RemboursementMapper.fromDTO(dto);
        remboursement.setCredit(credit);
        remboursement = remboursementRepository.save(remboursement);
        return RemboursementMapper.toDTO(remboursement);
    }

    @Override
    public RemboursementDTO updateRemboursement(Long id, RemboursementDTO dto) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement non trouvé"));
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(TypeRemboursement.valueOf(dto.getType()));
        remboursement = remboursementRepository.save(remboursement);
        return RemboursementMapper.toDTO(remboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement non trouvé"));
        return RemboursementMapper.toDTO(remboursement);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return credit.getRemboursements().stream()
                .map(RemboursementMapper::toDTO)
                .collect(Collectors.toList());
    }
}
