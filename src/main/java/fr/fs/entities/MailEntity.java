package fr.fs.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "MAIL")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MESSAGE")
    private Integer id;
    @Column(name = "SEND_AT")
    private LocalDate sendAt;

    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "BODY")
    private String body;
    @Column(name = "SENT_TO")
    private String SentTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "API_KEY")//cle etrang
    private ClientEntity client;//Apikey apikey;//
    ///join column//
//a 1 clt//
    //@Localdatetime//

//constructeur vide//
    public MailEntity() {
    }
//constructeur//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDate sendAt) {
        this.sendAt = sendAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}


