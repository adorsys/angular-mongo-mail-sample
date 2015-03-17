package de.adorsys.mob.sample.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import de.adorsys.mob.sample.model.Contact;
import de.adorsys.mob.sample.service.ContactService;

@Path("/contact")
@RequestScoped
public class ContactResource {

    @Inject
    private Logger log;

    @Inject
    private ContactService contactService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> list() {
        log.debug("list contacts");
        return contactService.list();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Contact contact) {
        log.debug("add contact");
        contactService.add(contact);
        return Response.status(Status.CREATED).build();
    }
}
