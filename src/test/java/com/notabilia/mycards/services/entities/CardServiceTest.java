package com.notabilia.mycards.services.entities;

import com.notabilia.mycards.models.entities.Card;
import com.notabilia.mycards.repositories.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository mockCardRepository;

    private CardService service;

    @Nested
    class GetAll {

        @BeforeEach
        void setUp() {
            service = new CardService(mockCardRepository);
        }

        @Test
        void shouldCallRepositoryFindAll() {
            // Given

            // When
            service.getAll();

            // Then
            verify(mockCardRepository, times(1)).findAll();
        }

        @Test
        void onExceptionShouldThrow() {
            // Given
            String message = "hello world";
            Exception e = new RuntimeException(message);

            // When
            when(mockCardRepository.findAll()).thenThrow(e);

            // Then
            Exception thrown = assertThrows(Exception.class, () -> service.getAll());
            assertTrue(thrown.getLocalizedMessage().contains(message));
        }

        @Test
        void shouldReturnAllCards() {
            // Given
            List<Card> cards = Arrays.asList(new Card(), new Card(), new Card());

            // When
            when(mockCardRepository.findAll()).thenReturn(cards);
            Iterable<Card> response = service.getAll();

            // Then
            assertThat(response.spliterator().getExactSizeIfKnown(), is(equalTo((long) cards.size())));
        }
    }

    @Nested
    class GetOne {

        @BeforeEach
        void setUp() {
            service = new CardService(mockCardRepository);
        }

        @Test
        void shouldCallRepositoryFindById() {
            // Given
            Integer id = 1;

            // When
            when(mockCardRepository.findById(anyInt())).thenReturn(Optional.of(new Card(id)));
            service.getOne(id);

            // Then
            verify(mockCardRepository, times(1)).findById(eq(id));
        }

        @Test
        void onEmptyShouldThrowException() {
            // Given

            // When
            when(mockCardRepository.findById(anyInt())).thenReturn(Optional.empty());

            // Then
            assertThrows(EntityNotFoundException.class, () -> service.getOne(1));
        }

        @Test
        void shouldReturnCorrectCard() {
            // Given
            Integer id = 1;
            Card message = new Card(id);

            // When
            when(mockCardRepository.findById(anyInt())).thenReturn(Optional.of(message));
            Card response = service.getOne(id);

            // Then
            assertThat(response.getId(), is(equalTo(id)));
        }
    }

    @Nested
    class CreateOne {

        @Mock
        Card mockCard;

        @BeforeEach
        void setUp() {
            service = new CardService(mockCardRepository);
        }

        @Test
        void onExistenceShouldThrowException() {
            // Given
            Integer id = 1;
            Card message = new Card(id);

            // When
            when(mockCardRepository.existsById(eq(id))).thenReturn(true);

            // Then
            assertThrows(EntityExistsException.class, () -> service.createOne(message));
        }

        @Test
        void shouldCallRepositorySave() {
            // Given
            Card message = new Card(null);

            // When
            service.createOne(message);

            // Then
            verify(mockCardRepository, times(1)).save(eq(message));
        }

        @Test
        void shouldReturnCorrectCard() {
            // Given
            Integer id = 1;
            Card message = new Card(id);

            // When
            Mockito.lenient().when(mockCardRepository.save(Mockito.any(Card.class))).thenReturn(message);
            Card response = service.createOne(message);

            // Then
            assertThat(response, is(equalTo(message)));
        }
    }
}