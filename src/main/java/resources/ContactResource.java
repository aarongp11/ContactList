package resources;

import data.Contact;
import services.ContactDAO;
import services.InMemoryDataStorage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/contacts")
public class
ContactResource {
//    @Inject
//    InMemoryDataStorage ds;
    @Inject
    ContactDAO ds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData() {
        return Response.ok(ds.retrieveAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/retrieve/{nif}")
    public Response getContact(@PathParam("nif") final String nif) {
        if (ds.retrieve(nif) == Contact.NOT_FOUND) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(ds.retrieve(nif)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createContact(Contact contact) throws URISyntaxException {
        Contact response = ds.create(contact);
        if(response == Contact.NOT_FOUND) return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/contacts/" + contact.getNIF());
        return Response.created(uri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateContact(final Contact contact) throws URISyntaxException {
        Contact result = ds.update(contact);
        if(result == Contact.NOT_FOUND) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{nif}")
    public Response deleteContact(@PathParam("nif") final String nif) {
        Contact result = ds.delete(nif);
        if(result == Contact.NOT_FOUND) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}