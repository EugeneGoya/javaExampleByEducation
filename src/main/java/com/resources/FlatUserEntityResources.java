package com.resources;

import com.codahale.metrics.annotation.Timed;
import com.db.FlatUserEntityDao;
import com.models.FlatUserEntity;
import com.service.ToolService;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class FlatUserEntityResources {
    private final String userFilePath = "src/main/resources/user.csv";
    private final String cityFilePath = "src/main/resources/city.csv";
    private final String countryFilePath = "src/main/resources/country.csv";

    private final FlatUserEntityDao flatUserEntityDao;

    private ToolService toolService;

    @Inject
    public FlatUserEntityResources(FlatUserEntityDao flatUserEntityDao, ToolService toolService) {
        this.flatUserEntityDao = flatUserEntityDao;
        this.toolService = toolService;
    }

    //    @POST
//    @UnitOfWork
//    public FlatUserEntity createPerson(@Valid FlatUserEntity person) {
//        return flatUserEntityDao.create(person);
//    }
//    @GET
//    @Path("/{id}")
//    @Timed
//    @UnitOfWork
//    public Optional<FlatUserEntity> findPerson(@PathParam("id") LongParam id) {
//        return flatUserEntityDao.findById(id.get());
//    }
    @GET
    @Path("/update")
    @UnitOfWork
    public Boolean listPeopleUpdateFromFile() {
        toolService.importData(userFilePath,cityFilePath,countryFilePath);
        return true;
    }
//    @GET
//    @UnitOfWork
//    public List<FlatUserEntity> listPeople() {
//        return flatUserEntityDao.findAll();
//    }
}
