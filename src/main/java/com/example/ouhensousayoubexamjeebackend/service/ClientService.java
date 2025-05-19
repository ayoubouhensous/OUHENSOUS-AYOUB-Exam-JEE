package com.example.ouhensousayoubexamjeebackend.service;

import com.example.ouhensousayoubexamjeebackend.dtos.ClientDTO;

import java.util.List;

public interface ClientService {

    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
}
