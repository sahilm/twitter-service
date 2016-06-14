package com.sahilm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;

public class Application {

    public static void main(final String[] args) throws Exception {
        final Server server = new Server(8080);
        final WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(new ClassPathResource("webapp").getURI().toString());
        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
