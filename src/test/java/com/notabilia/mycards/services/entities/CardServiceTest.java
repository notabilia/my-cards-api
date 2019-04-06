package com.notabilia.mycards.services.entities;

import com.notabilia.mycards.models.entities.Card;
import com.notabilia.mycards.repositories.CardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    private class TestException extends RuntimeException {}

    @Mock
    private CardRepository mockCardRepository;

    private CardService service;

    @Before
    public void setUp() {
        service = new CardService(mockCardRepository);
    }

    @Test
    public void shouldCallRepositoryFindAll() {
        // Given

        // When
        service.getAll();

        // Then
        verify(mockCardRepository, times(1)).findAll();
    }

    @Test(expected = TestException.class)
    public void onExceptionShouldThrow() {
        // Given
        when(mockCardRepository.findAll()).thenThrow(new TestException());

        // When
        service.getAll();

        // Then
        // Exception expected
    }

    @Test
    public void shouldReturnAllCards() {
        // Given
        List<Card> cards = Arrays.asList(new Card(), new Card(), new Card());

        // When
        when(mockCardRepository.findAll()).thenReturn(cards);
        Iterable<Card> response = service.getAll();

        // Then
        assertThat(response.spliterator().getExactSizeIfKnown(), is(equalTo((long) cards.size())));
    }

    @Test
    public void shouldCallRepositoryFindById() {
        // Given
        Integer id = 1;
        when(mockCardRepository.findById(anyInt())).thenReturn(Optional.of(new Card()));

        // When
        service.getOne(id);

        // Then
        verify(mockCardRepository, times(1)).findById(eq(id));
    }

    @Test(expected = EntityNotFoundException.class)
    public void onEmptyShouldThrowException() {
        // Given
        when(mockCardRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        service.getOne(1);

        // Then
        // Exception expected
    }

    @Test
    public void shouldReturnCorrectCard() {
        // Given
        Integer id = 1;
        Card card = new Card(id);
        when(mockCardRepository.findById(anyInt())).thenReturn(Optional.of(card));

        // When
        Card response = service.getOne(id);

        // Then
        assertThat(response.getId(), is(equalTo(id)));
    }

    @Test(expected = EntityExistsException.class)
    public void onExistenceShouldThrowException() {
        // Given
        Integer id = 1;
        Card card = new Card(id);
        when(mockCardRepository.existsById(eq(id))).thenReturn(true);

        // When
        service.createOne(card);

        // Then
        // Exception expected
    }

    @Test
    public void shouldCallRepositorySave() {
        // Given
        Card card = new Card(null);

        // When
        service.createOne(card);

        // Then
        verify(mockCardRepository, times(1)).save(eq(card));
    }
}
