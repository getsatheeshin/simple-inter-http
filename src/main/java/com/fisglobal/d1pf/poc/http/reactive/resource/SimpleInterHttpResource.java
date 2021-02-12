package com.fisglobal.d1pf.poc.http.reactive.resource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.fisglobal.d1pf.poc.http.model.EchoMO;
import com.fisglobal.d1pf.poc.http.reactive.svc.SimpleService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.concurrent.ThreadLocalRandom;

@Path("/innerhttp")
public class SimpleInterHttpResource {

    private static final Logger LOG = Logger.getLogger(SimpleInterHttpResource.class);

    @Inject
    SimpleService service;

    @Inject
    @RestClient
    RouteResourceClient routeResourceClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("echo/{name}")
    public EchoMO echo(@PathParam String name) {
        LOG.info("Echo "+name);
        return service.echo(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("chain/{name}")
    public EchoMO chain(@PathParam String name) {
        LOG.info("Chain "+name);
        EchoMO mo = service.echo(name);
        return routeResourceClient.route(mo);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("route/msg")
    public EchoMO echoroute(EchoMO mo) {
        LOG.info("Echoroute "+mo.getMsg());
        return service.routeEcho(mo);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ping")
    public String ping() {
        return "d1platform-innerhttp-ping::"+ ThreadLocalRandom.current().nextLong(0,Long.MAX_VALUE);
    }
}