package com.thoughtworks.exam.core;

import java.util.List;
import java.util.Map;

/**
 * Created by ctang on 4/21/16.
 */
public interface PublishedTemplatesRepository {
    List<PublishedTemplate> findPublishedTemplates();

    PublishedTemplate newPublishedTemplate(String name);

    int createLogicTemplate();
    void createLogicQuestionRule(Map newInstance);
    int createProgrammingQuestionPaper();
    void createProgrammingQuestion(Map newInstance);
    void createPublishedTemplate(Map newInstance);

    PublishedTemplate getPublishedTemplateById(int publishedTemplateId);
}
