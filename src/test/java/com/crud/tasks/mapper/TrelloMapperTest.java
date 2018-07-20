package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMapperTest {
    private static final String LIST_ID = "list_id";
    private static final String LIST_NAME = "list_name";
    private static final String BOARD_ID = "task_id";
    private static final String BOARD_NAME = "task_name";
    private static final String CARD_DESCRIPTION = "card_desc";
    private static final String CARD_NAME = "card_name";
    private static final String CARD_POS = "card_pos";
    private static final String CARD_LIST_ID = "card_list_id";


    private TrelloMapper mapper = new TrelloMapper();

    @Test
    public void mapToBoards() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto(LIST_ID, LIST_NAME, true);
        trelloListsDto.add(trelloListDto);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto(BOARD_ID, BOARD_NAME, trelloListsDto);
        trelloBoardDtos.add(trelloBoardDto);

        //when
        List<TrelloBoard> trelloBoards = mapper.mapToBoards(trelloBoardDtos);

        //then
        assertTrue(trelloBoards.size() == 1);
        TrelloBoard trelloBoard = trelloBoards.get(0);
        assertEquals(BOARD_ID, trelloBoard.getId());
        assertEquals(BOARD_NAME, trelloBoard.getName());
        TrelloList trelloList = trelloBoard.getLists().get(0);
        assertEquals(LIST_ID, trelloList.getId());
        assertEquals(LIST_NAME, trelloList.getName());
        assertTrue(trelloList.isClosed());
    }

    @Test
    public void mapToBoardsDto() {
        //given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList(LIST_ID, LIST_NAME, true);
        trelloLists.add(trelloList);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard(BOARD_ID, BOARD_NAME, trelloLists);
        trelloBoards.add(trelloBoard);

        //when
        List<TrelloBoardDto> trelloBoardsDto = mapper.mapToBoardsDto(trelloBoards);

        //then
        assertTrue(trelloBoardsDto.size() == 1);
        TrelloBoardDto trelloBoardDto = trelloBoardsDto.get(0);
        assertEquals(BOARD_ID, trelloBoardDto.getId());
        assertEquals(BOARD_NAME, trelloBoardDto.getName());
        TrelloListDto trelloListDto = trelloBoardDto.getLists().get(0);
        assertEquals(LIST_ID, trelloListDto.getId());
        assertEquals(LIST_NAME, trelloListDto.getName());
        assertTrue(trelloListDto.isClosed());
    }

    @Test
    public void mapToListDto() {
        //given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList(LIST_ID, LIST_NAME, true);
        trelloLists.add(trelloList);

        //when
        List<TrelloListDto> trelloListsDto = mapper.mapToListDto(trelloLists);

        //then
        assertTrue(trelloListsDto.size() == 1);
        TrelloListDto trelloListDto = trelloListsDto.get(0);
        assertEquals(LIST_ID, trelloListDto.getId());
        assertEquals(LIST_NAME, trelloListDto.getName());
        assertTrue(trelloListDto.isClosed());
    }

    @Test
    public void mapToList() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto(LIST_ID, LIST_NAME, true);
        trelloListsDto.add(trelloListDto);

        //when
        List<TrelloList> trelloLists = mapper.mapToList(trelloListsDto);

        //then
        assertTrue(trelloLists.size() == 1);
        TrelloList trelloList = trelloLists.get(0);
        assertEquals(LIST_ID, trelloList.getId());
        assertEquals(LIST_NAME, trelloList.getName());
        assertTrue(trelloList.isClosed());
    }

    @Test
    public void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard(CARD_NAME, CARD_DESCRIPTION, CARD_POS, CARD_LIST_ID);

        //when
        TrelloCardDto trelloCardDto = mapper.mapToCardDto(trelloCard);

        //then
        assertEquals(CARD_NAME, trelloCardDto.getName());
        assertEquals(CARD_DESCRIPTION, trelloCardDto.getDescription());
        assertEquals(CARD_POS, trelloCardDto.getPos());
        assertEquals(CARD_LIST_ID, trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto(CARD_NAME, CARD_DESCRIPTION, CARD_POS, CARD_LIST_ID);

        //when
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);

        //then
        assertEquals(CARD_NAME, trelloCard.getName());
        assertEquals(CARD_DESCRIPTION, trelloCard.getDescription());
        assertEquals(CARD_POS, trelloCard.getPos());
        assertEquals(CARD_LIST_ID, trelloCard.getListId());
    }
}