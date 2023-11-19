package fr.fs.dto;

import fr.fs.entities.ClientEntity;

public class ClientDto {
    String nom;
    String apiKey;

    public ClientDto(ClientEntity clientEntity) {
        this.nom = clientEntity.getNom();
        this.apiKey = clientEntity.getApiKey();
    }
}
