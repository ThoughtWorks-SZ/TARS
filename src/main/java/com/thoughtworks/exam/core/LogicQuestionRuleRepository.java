package com.thoughtworks.exam.core;

import java.util.List;
import java.util.Map;

/**
 * Created by ctang on 4/25/16.
 */
public interface LogicQuestionRuleRepository {
    List<LogicQuestionRule> findLogicQuestionRules();

    LogicQuestionRule newLogicQuestionRule();

    void createLogicQuestionRule(Map newInstance);

    LogicQuestionRule getLogicQuestionRuleById(String logicQuestionRuleId);

    List<LogicQuestionRule> getLogicQuestionRulesByTemplateId(String logicTemplateId);
}
