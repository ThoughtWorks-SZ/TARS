package com.thoughtworks.exam.core;

/**
 * Created by ctang on 4/22/16.
 */
public class LogicQuestionRule {
    private String id;
    private Integer level;
    private Integer amount;
    private String repositoryId;
    private String repositoryName;
    private String repositoryUrl;


    public String getId() {
        return this.id;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public String getRepositoryId() {
        return this.repositoryId;
    }

    public String getRepositoryName() {
        return this.repositoryName;
    }

    public String getRepositoryUrl() {
        return this.repositoryUrl;
    }
}
