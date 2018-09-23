package Database;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/managers/database")
public class DatabaseController {

    private IDatabaseService databaseService;
    private IDatabaseControllService databaseControllService;

    public DatabaseController() {
        databaseService = new DatabaseService();
        databaseControllService = new DatabaseControllService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String get(@Context HttpHeaders headers) {
        return databaseService.get().toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String post(@Context HttpHeaders headers, @FormParam("url") String url, @FormParam("username") String username, @FormParam("password") String password, @FormParam("dbName") String dbName, @FormParam("type") int type) {
        JSONObject x = new JSONObject();
        if (databaseService.create(url, username, password, dbName, type)) {
            x.put("status", "201");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String put(@Context HttpHeaders headers, @FormDataParam("url") String url, @FormDataParam("username") String username, @FormDataParam("password") String password, @FormDataParam("dbName") String dbName, @FormDataParam("type") int type, @FormDataParam("id") int id) {
        JSONObject x = new JSONObject();
        if (databaseService.update(id, url, username, password, dbName, type)) {
            x.put("status", "204");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String delete(@Context HttpHeaders headers, @PathParam("id") int id) {
        JSONObject x = new JSONObject();
        if (databaseService.delete(id)) {
            x.put("status", "200");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("setdatabase/{id}")
    public String setDB(@Context HttpHeaders headers, @PathParam("id") int id) {
        databaseControllService.setActive(id);
        return "{\"status\":\"200\"}";
    }
}