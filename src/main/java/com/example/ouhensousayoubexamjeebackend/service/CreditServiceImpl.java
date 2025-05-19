package com.example.ouhensousayoubexamjeebackend.service;

import com.example.ouhensousayoubexamjeebackend.dtos.CreditDTO;
import com.example.ouhensousayoubexamjeebackend.mappers.CreditMapper;
import com.example.ouhensousayoubexamjeebackend.models.Client;
import com.example.ouhensousayoubexamjeebackend.models.Credit;
import com.example.ouhensousayoubexamjeebackend.repository.ClientRepository;
import com.example.ouhensousayoubexamjeebackend.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository;
    private ClientRepository clientRepository;
    public CreditServiceImpl(CreditRepository creditRepository, ClientRepository clientRepository) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CreditDTO createCredit(CreditDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        Credit credit = CreditMapper.fromDTO(dto);
        credit.setClient(client);
        credit = creditRepository.save(credit);
        return CreditMapper.toDTO(credit);
    }



    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return CreditMapper.toDTO(credit);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return client.getCredits().stream()
                .map(CreditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll()
                .stream()
                .map(CreditMapper::toDTO)
                .collect(Collectors.toList());
    }
}
