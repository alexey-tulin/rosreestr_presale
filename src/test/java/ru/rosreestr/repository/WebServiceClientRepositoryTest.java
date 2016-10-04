package ru.rosreestr.repository;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.PersistentConfig;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistentConfig.class})
public class WebServiceClientRepositoryTest extends TestCase {

    private static final Logger LOGGER = Logger.getLogger(WebServiceClientRepositoryTest.class);

    @Resource
    private WebServiceRepository wsRepository;

    @Test
    public void testLog() throws Exception {

        LOGGER.info(wsRepository.findAll());

    }

    public void testLog1() throws Exception {

    }

    public void testLogXml() throws Exception {

    }
}