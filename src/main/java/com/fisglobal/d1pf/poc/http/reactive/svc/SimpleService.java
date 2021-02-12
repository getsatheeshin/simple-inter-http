package com.fisglobal.d1pf.poc.http.reactive.svc;

import com.fisglobal.d1pf.poc.http.model.EchoMO;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleService {

    public EchoMO echo(String name) {
        EchoMO mo = new EchoMO();
        mo.setMsg("d1platform-innerhttp-direct ==> " + name);
        return mo;
    }

    public EchoMO routeEcho(EchoMO mo) {
        mo.setRoutemsg("d1platform-innerhttp-routed ==> " + mo.getMsg());
        return mo;
    }

}
