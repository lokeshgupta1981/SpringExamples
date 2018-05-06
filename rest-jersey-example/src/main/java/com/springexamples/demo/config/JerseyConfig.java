package com.springexamples.demo.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.springexamples.demo.UserResource;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(UserResource.class);
    }
}
