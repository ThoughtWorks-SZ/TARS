package com.thoughtworks.exam.core;

import java.util.List;
import java.util.Map;

/**
 * Created by ctang on 4/23/16.
 */
public interface LogicTemplateRepository {
    List<LogicTemplate> findLogicTemplates();

    LogicTemplate newLogicTemplate();

    void createLogicTemplate(Map newInstance);

    LogicTemplate getLogicTemplateById(String logicTemplateId);
}
