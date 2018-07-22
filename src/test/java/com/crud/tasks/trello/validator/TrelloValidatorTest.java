package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloValidatorTest {

    private TrelloValidator validator = new TrelloValidator();

    @Test
    public void validateTrelloBoards() {
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1", "Name", null);
        TrelloBoard board2 = new TrelloBoard("2", "test", null);
        trelloBoards.add(board1);
        trelloBoards.add(board2);

        //when
        List<TrelloBoard> validatedBoards = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(1, validatedBoards.size());
        assertEquals("1", validatedBoards.get(0).getId());
    }
}