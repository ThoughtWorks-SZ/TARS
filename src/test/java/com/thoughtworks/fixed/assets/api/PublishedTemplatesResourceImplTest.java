package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.exam.api.model.PublishedTemplate;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplatesResourceImplTest extends TestBase {
    private String basePath = "/published-templates";
    private PublishedTemplate firstPublishedTemplate = new PublishedTemplate();
    private PublishedTemplate secondPublishedTemplate = new PublishedTemplate();
    private PublishedTemplate newPublishedTemplate = new PublishedTemplate();


    @Override
    public void setUp() throws Exception {
        firstPublishedTemplate.setId("f7b1d300-086a-11e6-99fe-0b01e67fc86e");
        firstPublishedTemplate.setName("This is a template.");

        secondPublishedTemplate.setId("f89747f0-086a-11e6-99fe-0b01e67fc86e");
        secondPublishedTemplate.setName("This is another template.");

        newPublishedTemplate.setName("This is third template.");

        when(publishedTemplatesRepository.findPublishedTemplates()).thenReturn(
                Arrays.asList(firstPublishedTemplate, secondPublishedTemplate));

        when(publishedTemplatesRepository.getPublishedTemplateById(anyString())).thenReturn(firstPublishedTemplate);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ((Map)arguments[0]).put("id", "f8f19caa-086a-11e6-99fe-0b01e67fc86e");
                return null;
            }
        }).when(publishedTemplatesRepository).createPublishedTemplate(anyMap());
        super.setUp();
    }

    @Test
    public void should_list_all_published_templates() throws Exception {
        Response response = target(basePath).request().get();

        assertThat(response.getStatus(), is(200));

        List<Map> result = response.readEntity(List.class);

        assertThat(result.size(), is(2));

        Map actualFirstTemplate = result.get(0);
        Map actualSecondTemplate = result.get(1);

        assertThat((String) actualFirstTemplate.get("id"), is(firstPublishedTemplate.getId()));
        assertThat((String) actualFirstTemplate.get("name"), is(firstPublishedTemplate.getName()));

        assertThat((String) actualSecondTemplate.get("id"), is(secondPublishedTemplate.getId()));
        assertThat((String) actualSecondTemplate.get("name"), is(secondPublishedTemplate.getName()));
    }

    @Test
    public void should_get_published_template_by_id() throws Exception {
        Response response = target(basePath+"/f7b1d300-086a-11e6-99fe-0b01e67fc86e").request().get();

        assertThat(response.getStatus(), is(200));

        Map actualTemplate = response.readEntity(Map.class);

        assertThat((String) actualTemplate.get("id"), is(firstPublishedTemplate.getId()));
        assertThat((String) actualTemplate.get("name"), is(firstPublishedTemplate.getName()));
    }

    @Test
    public void should_get_not_found_by_invalid_id() throws Exception {
        Response response = target(basePath+"/ffffffff-086a-11e6-99fe-0b01e67fc86e").request().get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_create_one_published_template() throws Exception {
        Response response = target(basePath).request().post(Entity.entity(newPublishedTemplate, "application/json"));

        assertThat(response.getStatus(), is(201));

        Map actualNewTemplate = response.readEntity(Map.class);
        assertThat(((String) actualNewTemplate.get("id")).length(), is(36));
        assertThat((String) actualNewTemplate.get("name"), is(firstPublishedTemplate.getName()));
    }
}
