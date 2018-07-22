package com.crud.tasks.trello;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {


    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void shouldCreateCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos","2323");
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos","2323");
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("id", "name", "shortUrl");
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCard);

        //when
        CreatedTrelloCardDto created = trelloFacade.createCard(trelloCardDto);

        //then
        Assert.assertEquals(createdTrelloCard, created);
    }
}