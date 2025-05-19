package com.example.ouhensousayoubexamjeebackend.service;

import com.example.ouhensousayoubexamjeebackend.dtos.ClientDTO;
import com.example.ouhensousayoubexamjeebackend.mappers.ClientMapper;
import com.example.ouhensousayoubexamjeebackend.models.Client;
import com.example.ouhensousayoubexamjeebackend.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public ClientDTO createClient(ClientDTO dto) {
        Client client = ClientMapper.fromDTO(dto);
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return ClientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> getClientsByName(String name) {
        return clientRepository.findByNomContains(name)
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());

    }
}
