package fr.fs.resources;

import fr.fs.dto.MailDto;
import fr.fs.entities.ClientEntity;
import fr.fs.entities.MailEntity;
import fr.fs.repositories.ClientRepository;
import fr.fs.repositories.MailRepository;
import fr.fs.tools.Validator;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/mails")
public class MailResources {

    //inject//
    @Inject
    Mailer mailer;
    //inject//
    @Inject
    MailRepository mailRepository;
    @Inject
    ClientRepository clientRepository;

    @GET
    @Operation(summary = "Mail by Id", description = "Search a color by its ID")
    @APIResponse(responseCode = "200", description = "Ok, mail found")
    @APIResponse(responseCode = "404", description = "Mail not found")
    @Path("{mails}")
    public Response getById(@PathParam("mails") Integer id) {
        MailEntity mailEntity = mailRepository.findById(id);
        if (mailEntity == null)
            return Response.status(404, "Cet identifiant n'existe pas !").build();
        else
            return Response.ok(mailEntity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendEmail(MailDto mail, @HeaderParam("apiKey") String apiKey) {

        ClientEntity clientEntity = clientRepository.findById(apiKey);

        if (clientEntity == null )
            return Response.status(Response.Status.UNAUTHORIZED).entity("Clef d'api non valide !").build();
        if (!Validator.isValideMail(mail.getTo()))
            return Response.ok("Adresse mail invalide !").status(400).build();
       /*if(clientEntity.getMailEntity()
               .stream()
               .filter(m -> m.getSendAt().)
               .count()>=5);*/

/*cop
        if(clientEntity.getMails()
                .stream()
                .filter(m -> m.getSendAt().toLocalDate().equals(locaDate.now())))
        .count(>=5
    return Response.ok("quotq dep".status(400).build);*/

       /* MailEntity mailEntity = new MailEntity((mail);
        mailEntity.setClient(clientEntity)
        mailRepository.persist(MailEntity);))*/
        //maiiler.sen...//
//        List<MailEntity> Mails;
        //

        MailEntity mailEntity = new MailEntity();
        mailEntity.setSendAt(mailEntity.getSendAt());
        mailEntity.setSubject(mailEntity.getSubject());
        mailEntity.setBody(mailEntity.getBody());
        mailEntity.setSendAt(mailEntity.getSendAt());
        /*mailEntity.setClient(ClientEntity)//contricteur clietn ds MailEn*/
        mailRepository.persist(mailEntity);

        mailer.send(Mail.withText(mail.getTo(),
                mailEntity.getSubject(),
                mailEntity.getBody()));
        //mailRepository.persist(new MailEntity().getSendAt(),MailEntity.);//
        return Response.ok(String.format("le message : %s, a été envoyé à : %s", mail.getSubject(), mail.getTo())).build();
    }
}
