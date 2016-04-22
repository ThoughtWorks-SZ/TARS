package com.thoughtworks.fixed.assets.api;

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
        assertThat(publishedTemplate.get(0).getTemplateId(), is(1));
        assertThat(publishedTemplate.get(0).getProgrammingPaperId(), is(1));
        assertThat(publishedTemplate.get(1).getName(), is("This is another template."));
        assertThat(publishedTemplate.get(1).getPublishedBy(), is(1));
        assertThat(publishedTemplate.get(1).getTemplateId(), is(1));
        assertThat(publishedTemplate.get(1).getProgrammingPaperId(), is(1));
    }

    @Test
    public void should_create_a_new_published_template() throws Exception {
        Map newInstance = new HashMap();
        newInstance.put("name", "This is the third template.");
        newInstance.put("published_by", "1");
        newInstance.put("logic_template_id", "1");
        newInstance.put("programming_question_paper_id", "1");
        publishedTemplatesRepository.createPublishedTemplate(newInstance);

        assertThat((String) newInstance.get("name"), is("This is the third template."));
        assertThat((Integer) newInstance.get("id"), is(not(0)));
    }

    @Test
    public void should_get_published_template_by_id() throws Exception {
        PublishedTemplate thePublishedTemplate = publishedTemplatesRepository.getPublishedTemplateById(1);
        assertThat(thePublishedTemplate.getId(), is(1));
        assertThat(thePublishedTemplate.getName(), is("This is a template."));
        assertThat(thePublishedTemplate.getPublishedBy(), is(1));
        assertThat(thePublishedTemplate.getTemplateId(), is(1));
        assertThat(thePublishedTemplate.getProgrammingPaperId(), is(1));
    }
}
