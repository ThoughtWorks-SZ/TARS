package com.thoughtworks.exam.core;

import java.util.List;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplate {
    private String name;
    private int id;
    private int publishedBy;
    private List<LogicQuestionRule> logicQuestionRules;
    private List<ProgrammingQuestion> programmingQuestions;

    public PublishedTemplate(Integer id, String name, Integer publishedBy, List<LogicQuestionRule> logicQuestionRules,
                             List<ProgrammingQuestion> programmingQuestions) {
        this.name = name;
        this.id = id;
        this.publishedBy = publishedBy;
        this.logicQuestionRules = logicQuestionRules;
        this.programmingQuestions = programmingQuestions;
    }

    public PublishedTemplate(String name, Integer publishedBy, List<LogicQuestionRule> logicQuestionRules,
                             List<ProgrammingQuestion> programmingQuestions) {
        this.name = name;
        this.publishedBy = publishedBy;
        this.logicQuestionRules = logicQuestionRules;
        this.programmingQuestions = programmingQuestions;
    }

    public PublishedTemplate() {}

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishedBy() { return this.publishedBy; }

    public List<LogicQuestionRule> getLogicQuestionRules() { return this.logicQuestionRules; }

    public List<ProgrammingQuestion> getProgrammingQuestions() { return this.programmingQuestions; }
}
