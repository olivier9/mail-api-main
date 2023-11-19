package fr.fs.repositories;

import fr.fs.entities.MailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MailRepository implements PanacheRepositoryBase<MailEntity,Integer> {
}
