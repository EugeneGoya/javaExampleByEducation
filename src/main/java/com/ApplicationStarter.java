package com;

import com.health.TemplateHealthCheck;
import com.resources.FlatUserEntityResources;
import com.resources.SimpleServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ApplicationStarter extends Application<SimpleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApplicationStarter().run(args);
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
        environment.jersey().register(new FlatUserEntityResources());

    }

    @Override
    public String getName() {
        return "application";
    }
}