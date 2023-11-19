package fr.fs.resources;

import fr.fs.entities.ClientEntity;
import fr.fs.repositories.ClientRepository;
import fr.fs.repositories.MailRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import java.util.List;

@Path("/apikey/")
@Tag(name = "client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientResources {

    @Inject
    private ClientRepository clientRepository;

    //inject//
    @Inject    MailRepository mailRepository;

    @Path("/clients")

    @GET
    public Response getAll() {
        List<ClientEntity> clientEntities = clientRepository.listAll();
        return Response.ok(clientEntities).build();
    }
/*get get id/mail
                post client*/
        @GET
        @Operation(summary = "Client by Id", description = "Search a client by its APIKey")
        @APIResponse(responseCode = "200", description = "Ok, color found")
        @APIResponse(responseCode = "404", description = "Color not found")
        @Path("{apiKey}")
        public Response getByapiKey(@PathParam("apiKey") String apiKey)  {
            ClientEntity client = clientRepository.findById(apiKey);
            if (client == null)
                return Response.status(404,"Cet identifiant n'existe pas !").build();
            else
                return Response.ok(client).build();
        }
    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500", description = "Le serveur a rencontré un problème !")
    public Response create(ClientEntity clientEntity, @Context UriInfo uriInfo) {

        ClientEntity client = new ClientEntity();
        client.setNom(clientEntity.getNom());
        client.setApiKey(client.getApiKey());
        clientRepository.persist(client);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(String.valueOf(client.getApiKey()));
        return Response.created(uriBuilder.build()).build();
    }
/*
    @Transactional
    @DELETE
    @APIResponse(responseCode = "204", description = "La ressource a été supprimée !")
    @APIResponse(responseCode = "404", description = "La ressource n'existe pas ! !")
    @Path("{apiKey}")
    public Response delete(@jakarta.ws.rs.PathParam("apiKey") Integer id) {
        if (!ClientRepository.deleteByapiKey(apiKey))
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.noContent().build();
    }
    */
    }


