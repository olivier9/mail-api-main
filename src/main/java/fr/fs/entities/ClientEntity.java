package fr.fs.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "APIKEY")

public class ClientEntity {
    @Id
    @Column(name = "API_KEY")
    private String apiKey;

    @Column(name = "NOM")
    private String nom;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client") //FETCH mmapedby client//
    private List<MailEntity> mailEntity;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<MailEntity> getMailEntity() {
        return mailEntity;
    }

    public void setMailEntity(List<MailEntity> mailEntity) {
        this.mailEntity = mailEntity;
    }
}
