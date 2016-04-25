package com.thoughtworks.exam.core;

/**
 * Created by ctang on 4/22/16.
 */
public class ProgrammingQuestion {
    private String id;
    private String content;
    private String answer;
    private Integer durationHour;

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public String getAnswer() {
        return this.answer;
    }

    public Integer getDurationHour() {
        return this.durationHour;
    }
}
