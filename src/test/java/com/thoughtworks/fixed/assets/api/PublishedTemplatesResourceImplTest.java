package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.exam.api.model.PublishedTemplate;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
    private PublishedTemplate firstPublishedTemplate = mock(PublishedTemplate.class);
    private PublishedTemplate secondPublishedTemplate = mock(PublishedTemplate.class);
    private PublishedTemplate newPublishedTemplate = mock(PublishedTemplate.class);


    @Override
    public void setUp() throws Exception {
        when(publishedTemplatesRepository.findPublishedTemplates()).thenReturn(Arrays.asList(firstPublishedTemplate, secondPublishedTemplate));
        when(firstPublishedTemplate.getId()).thenReturn("f7b1d300-086a-11e6-99fe-0b01e67fc86e");
        when(firstPublishedTemplate.getName()).thenReturn("This is a template.");

        when(newPublishedTemplate.getId()).thenReturn("f89747f0-086a-11e6-99fe-0b01e67fc86e");
        when(newPublishedTemplate.getName()).thenReturn("This is the third template.");
        when(publishedTemplatesRepository.newPublishedTemplate(anyString())).thenReturn(mock(PublishedTemplate.class));

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

        Map publishedTemplate = result.get(0);

        assertThat((String) publishedTemplate.get("uri"), is(basePath+"/"+firstPublishedTemplate.getId()));
        assertThat((String) publishedTemplate.get("name"), is(firstPublishedTemplate.getName()));
    }
}
