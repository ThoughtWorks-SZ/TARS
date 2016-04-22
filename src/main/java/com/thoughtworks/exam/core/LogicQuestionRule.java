package com.thoughtworks.exam.core;

/**
 * Created by ctang on 4/22/16.
 */
public class LogicQuestionRule {
    private Integer id;
    private Integer level;
    private Integer amount;
    private Integer repositoryId;
    private String repositoryName;
    private String repositoryUrl;


    public Integer getId() {
        return this.id;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Integer getRepositoryId() {
        return this.repositoryId;
    }

    public String getRepositoryName() {
        return this.repositoryName;
    }

    public String getRepositoryUrl() {
        return this.repositoryUrl;
    }
}
