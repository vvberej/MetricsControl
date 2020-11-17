package ru.sbrf;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MetricsControlApplication extends Application<MetricsControlConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MetricsControlApplication().run(args);
    }

    @Override
    public String getName() {
        return "MetricsControl";
    }

    @Override
    public void initialize(final Bootstrap<MetricsControlConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MetricsControlConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
