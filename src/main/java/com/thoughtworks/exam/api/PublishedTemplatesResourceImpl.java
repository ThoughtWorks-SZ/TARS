package com.thoughtworks.exam.api;

import com.thoughtworks.exam.api.model.LogicQuestionRule;
import com.thoughtworks.exam.api.model.ProgrammingQuestion;
import com.thoughtworks.exam.api.model.PublishedTemplate;
import com.thoughtworks.exam.api.resource.PublishedTemplatesResource;

import com.thoughtworks.exam.core.PublishedTemplatesRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplatesResourceImpl implements PublishedTemplatesResource {

    @Inject
    private PublishedTemplatesRepository publishedTemplatesRepository;

    @Override
    public GetPublishedTemplatesResponse getPublishedTemplates() throws Exception {
        List<PublishedTemplate> publishedTemplates = publishedTemplatesRepository.findPublishedTemplates();
        return GetPublishedTemplatesResponse.withJsonOK(publishedTemplates);
    }

    @Override
    public PostPublishedTemplatesResponse postPublishedTemplates(PublishedTemplate entity) throws Exception {
        Map newLogicTemplate = new HashMap();
        newLogicTemplate.put("templateId", "");
        publishedTemplatesRepository.createLogicTemplate(newLogicTemplate);
        String templateId = (String)newLogicTemplate.get("templateId");

        Map newProgrammingQuestionPaper = new HashMap();
        newProgrammingQuestionPaper.put("paperId", "");
        publishedTemplatesRepository.createProgrammingQuestionPaper(newProgrammingQuestionPaper);
        String paperId = (String)newProgrammingQuestionPaper.get("paperId");

        for (LogicQuestionRule logicQuestionRule: entity.getLogicQuestionRules()) {
            Map newLogicQuestionRuleInstance = new HashMap();
            newLogicQuestionRuleInstance.put("id", "");
            newLogicQuestionRuleInstance.put("level", logicQuestionRule.getLevel());
            newLogicQuestionRuleInstance.put("amount", logicQuestionRule.getAmount());
            newLogicQuestionRuleInstance.put("repositoryId", logicQuestionRule.getRepoId());
            newLogicQuestionRuleInstance.put("templateId", templateId);
            publishedTemplatesRepository.createLogicQuestionRule(newLogicQuestionRuleInstance);
        }

        for (ProgrammingQuestion programmingQuestion: entity.getProgrammingQuestions()) {
            Map newProgrammingQuestionInstance = new HashMap();
            newProgrammingQuestionInstance.put("id", "");
            newProgrammingQuestionInstance.put("content", programmingQuestion.getContent());
            newProgrammingQuestionInstance.put("answer", programmingQuestion.getAnswer());
            newProgrammingQuestionInstance.put("durationHour", programmingQuestion.getDurationHour());
            newProgrammingQuestionInstance.put("paperId", paperId);
            publishedTemplatesRepository.createProgrammingQuestion(newProgrammingQuestionInstance);
        }

        Map newInstance = new HashMap();
        newInstance.put("id", "");
        newInstance.put("name", entity.getName());
        newInstance.put("publishedBy", entity.getPublishedBy());
        newInstance.put("templateId", templateId);
        newInstance.put("paperId", paperId);
        publishedTemplatesRepository.createPublishedTemplate(newInstance);

        PublishedTemplate newEntity = publishedTemplatesRepository.getPublishedTemplateById((String) newInstance.get("id"));

        return PostPublishedTemplatesResponse.withJsonCreated(newEntity);
    }

    @Override
    public GetPublishedTemplatesByPublishedTemplateIdResponse getPublishedTemplatesByPublishedTemplateId(String publishedTemplateId) throws Exception {

        PublishedTemplate publishedTemplate = publishedTemplatesRepository.getPublishedTemplateById(publishedTemplateId);
        if (publishedTemplate.getId().equals(publishedTemplateId)) {
            return GetPublishedTemplatesByPublishedTemplateIdResponse.withJsonOK(publishedTemplate);
        } else {
            return GetPublishedTemplatesByPublishedTemplateIdResponse.withNotFound();
        }
    }
}
