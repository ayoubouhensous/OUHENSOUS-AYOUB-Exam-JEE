package mappers;

import dtos.ClientDTO;
import models.Client;

import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        if (client.getCredits() != null) {
            dto.setCredits(
                    client.getCredits().stream()
                            .map(CreditMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static Client fromDTO(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        // On ne mappe pas les crédits ici pour éviter les boucles
        return client;
    }
}
