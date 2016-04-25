package com.thoughtworks.fixed.assets.api;

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
 * Created by ctang on 4/23/16.
 */
public class LogicTemplateRepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private LogicTemplateRepository logicTemplateRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        logicTemplateRepository = session.getMapper(LogicTemplateRepository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }

    @Test
    public void should_find_all_logic_templates() throws Exception {
        List<LogicTemplate> logicTemplates = logicTemplateRepository.findLogicTemplates();

        assertThat(logicTemplates.get(0).getId(), is("211e9310-0869-11e6-99fe-0b01e67fc86e"));
    }

    @Test
    public void should_create_a_new_logic_template() throws Exception {
        Map newInstance = new HashMap();
        newInstance.put("id", "");
        logicTemplateRepository.createLogicTemplate(newInstance);

        assertThat(((String)newInstance.get("id")).length(), is(36));
    }

    @Test
    public void should_get_logic_template_by_id() throws Exception {
        LogicTemplate logicTemplate = logicTemplateRepository.getLogicTemplateById("211e9310-0869-11e6-99fe-0b01e67fc86e");

        assertThat(logicTemplate.getId(), is("211e9310-0869-11e6-99fe-0b01e67fc86e"));
    }
}
