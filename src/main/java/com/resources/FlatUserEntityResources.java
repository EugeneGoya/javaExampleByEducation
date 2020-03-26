package com.resources;

import com.codahale.metrics.annotation.Timed;
import com.db.FlatEntityDao;
import com.db.FlatUserEntity;
import com.service.ToolService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/flatUser")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class FlatUserEntityResources {
    private final String userFilePath = "src/main/resources/user.csv";
    private final String cityFilePath = "src/main/resources/city.csv";
    private final String countryFilePath = "src/main/resources/country.csv";
    private ToolService toolService = new ToolService();
    private FlatEntityDao flatEntityDao = new FlatEntityDao();

    @GET
    @Timed
    @Path("/get")
    public List<FlatUserEntity> getPerson() {
        return flatEntityDao.printAllFlatUsers();

    }

    @GET
    @Timed
    @Path("/insert")
    public String insertPerson() {
        toolService.importData(userFilePath, cityFilePath, countryFilePath);
        return "Import data from file ";
    }

}