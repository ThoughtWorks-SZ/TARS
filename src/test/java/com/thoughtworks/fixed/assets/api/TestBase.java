package com.thoughtworks.fixed.assets.api;


import com.thoughtworks.exam.api.PublishedTemplatesResourceImpl;
import com.thoughtworks.exam.core.PublishedTemplatesRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.core.Application;

import static org.mockito.Mockito.mock;

public class TestBase extends JerseyTest {
    protected PublishedTemplatesRepository publishedTemplatesRepository = mock(PublishedTemplatesRepository.class);

    @Override
    protected Application configure() {
        
//        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
//        enable(TestProperties.RECORD_LOG_LEVEL);
        ResourceConfig config = new ResourceConfig();
        config.register(PublishedTemplatesResourceImpl.class);
        return config.register(new AbstractBinder() {


            @Override
            protected void configure() {

                bind(publishedTemplatesRepository).to(PublishedTemplatesRepository.class);


            }
        }).packages("com.thoughtworks.exam.api");
    }
}
