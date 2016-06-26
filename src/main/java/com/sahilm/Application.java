package com.sahilm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;

public class Application {

    private static Server server;

    public static void main(final String[] args) throws Exception {
        start(JoinWithMainThread.YES);
    }

    public static void start(final JoinWithMainThread joinWithMainThread) throws Exception {
        server = new Server(8080);
        final WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(new ClassPathResource("webapp").getURI().toString());
        server.setHandler(webapp);
        server.start();
        if (joinWithMainThread.equals(JoinWithMainThread.YES)) {
            server.join();
        }
    }

    public static void stop() throws Exception {
        server.stop();
    }

    public enum JoinWithMainThread {
        YES,
        NO
    }
}
