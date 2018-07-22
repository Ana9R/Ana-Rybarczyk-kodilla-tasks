package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void shouldGetTrelloApiEndpoint() {
        //when
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();

        //then
        assertEquals("https://api.trello.com/1", trelloApiEndpoint);
    }

    @Test
    public void shouldGetTrelloAppKey() {
        //when
        String trelloAppKey = trelloConfig.getTrelloAppKey();

        //then
        assertEquals("6af61c5c9254a76c3893a0cadc635ab8", trelloAppKey);
    }

    @Test
    public void shouldGetTrelloToken() {
        //when
        String trelloToken = trelloConfig.getTrelloToken();

        //then
        assertEquals("dedd0401b319017c43b5aab1bd4abeb37d30d5fa96fdbd17912e50740c5deca4", trelloToken);
    }

    @Test
    public void shouldGetTrelloUsername() {
        //when
        String trelloUsername = trelloConfig.getTrelloUsername();

        //then
        assertEquals("gucio_289", trelloUsername);
    }
}