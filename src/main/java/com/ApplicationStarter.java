package com;


import com.db.FlatUserEntityDao;
import com.models.FlatUserEntity;
import com.health.TemplateHealthCheck;
import com.resources.FlatUserEntityResources;
import com.service.ToolService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.resources.SimpleServiceResource;

public class ApplicationStarter extends Application<SimpleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApplicationStarter().run(args);
    }


    private final HibernateBundle<SimpleServiceConfiguration> hibernate = new HibernateBundle<SimpleServiceConfiguration>(FlatUserEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(SimpleServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<SimpleServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }


    @Override
    public void run(SimpleServiceConfiguration configuration, Environment environment) throws Exception {
        final SimpleServiceResource resource = new SimpleServiceResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        final FlatUserEntityDao dao = new FlatUserEntityDao(hibernate.getSessionFactory());
        final ToolService toolService = new ToolService(dao);
        environment.jersey().register(new FlatUserEntityResources(dao, toolService));

    }


    @Override
    public String getName() {
        return "application";
    }
}