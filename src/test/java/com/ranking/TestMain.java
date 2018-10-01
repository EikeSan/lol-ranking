package com.ranking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class TestMain {

    @Test
    public void contextLoads(){

    }
}
