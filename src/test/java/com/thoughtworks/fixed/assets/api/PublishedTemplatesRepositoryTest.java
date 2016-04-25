package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.exam.core.LogicQuestionRule;
import com.thoughtworks.exam.core.ProgrammingQuestion;
import com.thoughtworks.exam.core.PublishedTemplate;
import com.thoughtworks.exam.core.PublishedTemplatesRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by ctang on 4/21/16.
 */
public class PublishedTemplatesRepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private PublishedTemplatesRepository publishedTemplatesRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        publishedTemplatesRepository = session.getMapper(PublishedTemplatesRepository.class);
    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }

    @Test
    public void should_find_all_published_templates() throws Exception {
        List<PublishedTemplate> publishedTemplate = publishedTemplatesRepository.findPublishedTemplates();
        assertThat(publishedTemplate.size(), is(2));
        assertThat(publishedTemplate.get(0).getName(), is("This is a template."));
        assertThat(publishedTemplate.get(0).getPublishedBy(), is(1));
        assertThat(publishedTemplate.get(1).getName(), is("This is another template."));
        assertThat(publishedTemplate.get(1).getPublishedBy(), is(1));
    }

    @Test
    public void should_create_a_new_published_template() throws Exception {
        Map newLogicTemplate = new HashMap();
        newLogicTemplate.put("templateId", "");
        publishedTemplatesRepository.createLogicTemplate(newLogicTemplate);
        String templateId = (String)newLogicTemplate.get("templateId");
        assertThat(templateId.length(), is(36));

        Map newProgrammingQuestionPaper = new HashMap();
        newProgrammingQuestionPaper.put("paperId", "");
        publishedTemplatesRepository.createProgrammingQuestionPaper(newProgrammingQuestionPaper);
        String paperId = (String)newProgrammingQuestionPaper.get("paperId");
        assertThat(paperId.length(), is(36));

        Map newLogicQuestionRuleInstance = new HashMap();
        newLogicQuestionRuleInstance.put("id", "8569415e-086d-11e6-99fe-0b01e67fc86e");
        newLogicQuestionRuleInstance.put("level", 0);
        newLogicQuestionRuleInstance.put("amount", 2);
        newLogicQuestionRuleInstance.put("repositoryId", 1);
        newLogicQuestionRuleInstance.put("templateId", templateId);
        publishedTemplatesRepository.createLogicQuestionRule(newLogicQuestionRuleInstance);

        assertThat((Integer)newLogicQuestionRuleInstance.get("level"), is(0));
        assertThat((Integer)newLogicQuestionRuleInstance.get("amount"), is(2));
        assertThat(((String)newLogicQuestionRuleInstance.get("id")).length(), is(36));

        Map newProgrammingQuestionInstance = new HashMap();
        newProgrammingQuestionInstance.put("id", "9abd11ac-086d-11e6-99fe-0b01e67fc86e");
        newProgrammingQuestionInstance.put("content", "Design a program to output hello world.");
        newProgrammingQuestionInstance.put("answer", "localhost:3011/ci");
        newProgrammingQuestionInstance.put("durationHour", 148);
        newProgrammingQuestionInstance.put("paperId", paperId);
        publishedTemplatesRepository.createProgrammingQuestion(newProgrammingQuestionInstance);

        assertThat((String)newProgrammingQuestionInstance.get("content"), is("Design a program to output hello world."));
        assertThat(((String)newProgrammingQuestionInstance.get("id")).length(), is(36));

        Map newInstance = new HashMap();
        newInstance.put("id", "cbd59eb2-086d-11e6-99fe-0b01e67fc86e");
        newInstance.put("name", "This is the third template.");
        newInstance.put("publishedBy", "1");
        newInstance.put("templateId", templateId);
        newInstance.put("paperId", paperId);
        publishedTemplatesRepository.createPublishedTemplate(newInstance);

        assertThat((String) newInstance.get("name"), is("This is the third template."));
        assertThat(((String) newInstance.get("id")).length(), is(36));
    }

    @Test
    public void should_get_published_template_by_id() throws Exception {
        PublishedTemplate thePublishedTemplate =
                publishedTemplatesRepository.getPublishedTemplateById("823efe78-0869-11e6-99fe-0b01e67fc86e");
        assertThat(thePublishedTemplate.getId(), is("823efe78-0869-11e6-99fe-0b01e67fc86e"));
        assertThat(thePublishedTemplate.getName(), is("This is a template."));
        assertThat(thePublishedTemplate.getPublishedBy(), is(1));
        List<LogicQuestionRule> logicQuestionRules = thePublishedTemplate.getLogicQuestionRules();
        assertThat(logicQuestionRules.get(0).getLevel(), is(0));
        assertThat(logicQuestionRules.get(0).getAmount(), is(3));
        assertThat(logicQuestionRules.get(0).getRepositoryName(), is("Mathematical Logic"));
        assertThat(logicQuestionRules.get(0).getRepositoryUrl(), is("localhost:3010/mathematical-logic"));
        List<ProgrammingQuestion> programmingQuestions = thePublishedTemplate.getProgrammingQuestions();
        assertThat(programmingQuestions.get(0).getContent(), is("h1. This is a PROGRAMMING Exam"));
        assertThat(programmingQuestions.get(0).getAnswer(), is("localhost:3011/test-ci"));
        assertThat(programmingQuestions.get(0).getDurationHour(), is(148));
    }
}
