package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {


    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void shouldFetchTrelloBoards() {
        //when
        trelloService.fetchTrelloBoards();

        //then
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    public void shouldCreateTrelloCard() {
        // given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "234");

        //when
        trelloService.createTrelloCard(trelloCardDto);

        //then
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
    }
}