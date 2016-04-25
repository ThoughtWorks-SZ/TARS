package com.thoughtworks.exam.core;

import java.util.Date;
import java.util.List;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplate {
    private String id;
    private String name;
    private int publishedBy;
    private Date publishedDate;
    private String templateId;
    private String paperId;
    private List<LogicQuestionRule> logicQuestionRules;
    private List<ProgrammingQuestion> programmingQuestions;

    public PublishedTemplate(String id, String name, Integer publishedBy, Date publishedDate, String templateId,
                             String paperId, List<LogicQuestionRule> logicQuestionRules,
                             List<ProgrammingQuestion> programmingQuestions) {
        this.name = name;
        this.id = id;
        this.publishedBy = publishedBy;
        this.publishedDate = publishedDate;
        this.templateId = templateId;
        this.paperId = paperId;
        this.logicQuestionRules = logicQuestionRules;
        this.programmingQuestions = programmingQuestions;
    }

    public PublishedTemplate(String name, Integer publishedBy, Date publishedDate, String templateId, String paperId,
                             List<LogicQuestionRule> logicQuestionRules, List<ProgrammingQuestion> programmingQuestions) {
        this.name = name;
        this.publishedBy = publishedBy;
        this.publishedDate = publishedDate;
        this.templateId = templateId;
        this.paperId = paperId;
        this.logicQuestionRules = logicQuestionRules;
        this.programmingQuestions = programmingQuestions;
    }

    public PublishedTemplate() {}

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishedBy() { return this.publishedBy; }

    public Date getPublishedDate() { return this.publishedDate; }

    public String getTemplateId() { return this.templateId; }

    public String getPaperId() { return this.paperId; }

    public List<LogicQuestionRule> getLogicQuestionRules() { return this.logicQuestionRules; }

    public List<ProgrammingQuestion> getProgrammingQuestions() { return this.programmingQuestions; }
}
