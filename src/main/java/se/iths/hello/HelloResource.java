package se.iths.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDate;

@Path("sayhelloto")
public class HelloResource {

    @Path("{name}")
    @GET
    public String hello(@PathParam("name") String name) {
        return "Hello " + name + "!";
    }

}
