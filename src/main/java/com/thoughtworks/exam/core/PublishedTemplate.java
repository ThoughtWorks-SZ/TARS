package com.thoughtworks.exam.core;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplate {
    private String name;
    private int id;
    private int publishedBy;
    private int templateId;
    private int programmingPaperId;

    public PublishedTemplate(Integer id, String name, Integer publishedBy, Integer templateID, Integer programmingPaperID) {
        this.name = name;
        this.id = id;
        this.publishedBy = publishedBy;
        this.templateId = templateID;
        this.programmingPaperId = programmingPaperID;
    }

    public PublishedTemplate(String name, Integer publishedBy, Integer templateID, Integer programmingPaperID) {
        this.name = name;
        this.publishedBy = publishedBy;
        this.templateId = templateID;
        this.programmingPaperId = programmingPaperID;
    }

    public PublishedTemplate() {}

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishedBy() { return this.publishedBy; }

    public int getTemplateId() { return this.templateId; }

    public int getProgrammingPaperId() { return this.programmingPaperId; }
}
