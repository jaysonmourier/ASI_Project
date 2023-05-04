package com.miage;

import com.miage.controllers.HelloWorldController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        packages("com.miage.controllers");
        register(HelloWorldController.class);
        register(CORSFilter.class);
    }
}
