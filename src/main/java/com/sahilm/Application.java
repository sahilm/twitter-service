package com.sahilm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;

public class Application {

    private static Server server;

    public static void main(final String[] args) throws Exception {
        start(true);
    }

    public static void start(final boolean join) throws Exception {
        server = new Server(8080);
        final WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(new ClassPathResource("webapp").getURI().toString());
        server.setHandler(webapp);
        server.start();
        if (join) {
            server.join();
        }
    }

    public static void stop() throws Exception {
        server.stop();
    }
}
