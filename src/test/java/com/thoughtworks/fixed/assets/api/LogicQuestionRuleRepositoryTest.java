package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.exam.core.LogicQuestionRule;
import com.thoughtworks.exam.core.LogicQuestionRuleRepository;
import com.thoughtworks.exam.core.LogicTemplate;
import com.thoughtworks.exam.core.LogicTemplateRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ctang on 4/25/16.
 */
public class LogicQuestionRuleRepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private LogicQuestionRuleRepository logicQuestionRuleRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        logicQuestionRuleRepository = session.getMapper(LogicQuestionRuleRepository.class);
    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();
    }

    @Test
    public void should_find_all_logic_question_rules() throws Exception {
        List<LogicQuestionRule> logicQuestionRules = logicQuestionRuleRepository.findLogicQuestionRules();

        assertThat(logicQuestionRules.get(0).getId(), is("5059a606-0869-11e6-99fe-0b01e67fc86e"));

    }

    @Test
    public void should_create_a_new_logic_question_rule() throws Exception {
        Map newInstance = new HashMap();
        newInstance.put("id", "");
        newInstance.put("level", 3);
        newInstance.put("amount", 5);
        newInstance.put("repo_id", "3a8e05f6-0869-11e6-99fe-0b01e67fc86e");
        newInstance.put("template_id","211e9310-0869-11e6-99fe-0b01e67fc86e");
        logicQuestionRuleRepository.createLogicQuestionRule(newInstance);

        assertThat(((String)newInstance.get("id")).length(), is(36));
    }

    @Test
    public void should_get_logic_question_rule_by_id() throws Exception {
        LogicQuestionRule logicQuestionRule =
                logicQuestionRuleRepository.getLogicQuestionRuleById("50c77d02-0869-11e6-99fe-0b01e67fc86e");

        assertThat(logicQuestionRule.getId(), is("50c77d02-0869-11e6-99fe-0b01e67fc86e"));
        assertThat(logicQuestionRule.getLevel(), is(0));
        assertThat(logicQuestionRule.getAmount(), is(4));
        assertThat(logicQuestionRule.getRepositoryName(), is("Thinking Logic"));
    }

    @Test
    public void should_get_logic_question_rules_by_template_id() throws Exception {
        List<LogicQuestionRule> logicQuestionRules =
                logicQuestionRuleRepository.getLogicQuestionRulesByTemplateId("211e9310-0869-11e6-99fe-0b01e67fc86e");

        assertThat(logicQuestionRules.size(), is(4));
        assertThat(logicQuestionRules.get(0).getId(), is("5059a606-0869-11e6-99fe-0b01e67fc86e"));
        assertThat(logicQuestionRules.get(0).getRepositoryName(), is("Mathematical Logic"));
    }
}
