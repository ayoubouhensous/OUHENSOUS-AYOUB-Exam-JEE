package service;

import dtos.ClientDTO;
import mappers.ClientMapper;
import models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

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
}
