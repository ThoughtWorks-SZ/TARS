package com.thoughtworks.exam.api;

import com.thoughtworks.exam.api.model.PublishedTemplate;
import com.thoughtworks.exam.api.model.PublishedTemplates;
import com.thoughtworks.exam.api.resource.PublishedTemplatesResource;

import com.thoughtworks.exam.core.PublishedTemplatesRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplatesResourceImpl implements PublishedTemplatesResource {

    @Inject
    private PublishedTemplatesRepository pubishedTemplatesRepository;

    @Override
    public GetPublishedTemplatesResponse getPublishedTemplates() throws Exception {
        List<PublishedTemplate> publishedTemplates = pubishedTemplatesRepository.findPublishedTemplates();
        return GetPublishedTemplatesResponse.withJsonOK(new PublishedTemplates().withPublishedTemplates(publishedTemplates));
    }

    @Override
    public PostPublishedTemplatesResponse postPublishedTemplates(PublishedTemplate entity) throws Exception {
        return null;
    }
}
