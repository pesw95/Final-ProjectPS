package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll(); // cleanse
    }

    /* testing get, create, delete */
    @Test
    public void getAddDeleteGame() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getGameId());

        assertEquals(game1.get(), game);

        gameRepository.deleteById(game.getGameId());

        game1 = gameRepository.findById(game.getGameId());

        assertFalse(game1.isPresent());
    }

    /* testing find all games */
    @Test
    public void shouldGetAllGames() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        Game game2 = new Game();
        game2.setTitle("Knight");
        game2.setEsrbRating("E10+");
        game2.setDescription("Video game developed and published by Team Cherry");
        game2.setPrice(new BigDecimal("19.99"));
        game2.setStudio("Team Cherry");
        game2.setQuantity(70);

        gameRepository.save(game2);

        assertEquals(2, gameRepository.findAll().size());
    }

    /* testing update game */
    @Test
    public void shouldUpdateGame() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        game.setTitle("GAME TITLE UPDATE");
        game.setQuantity(100);

        gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getGameId());
        assertEquals(game1.get(), game);
    }

    /* testing search by studio */
    @Test
    public void shouldGetAllGamesByStudio() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        Game game2 = new Game();
        game2.setTitle("New Team Cherry Game");
        game2.setEsrbRating("E10+");
        game2.setDescription("Video game by Team Cherry");
        game2.setPrice(new BigDecimal("60.00"));
        game2.setStudio("Team Cherry");
        game2.setQuantity(70);

        gameRepository.save(game2);

        List<Game> foundGames = gameRepository.findAllGamesByStudio(game.getStudio());

        // added two games
        assertEquals(2, foundGames.size());

        // assigning game2 to a game object
        Game foundGame = foundGames.get(1);

        // making sure they are equal
        assertEquals(foundGame, game2);
    }


    /* testing search by title */
    @Test
    public void shouldGetAllGamesByTitle() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        Game game2 = new Game();
        game2.setTitle("New Team Cherry Game");
        game2.setEsrbRating("E10+");
        game2.setDescription("Video game by Team Cherry");
        game2.setPrice(new BigDecimal("60.00"));
        game2.setStudio("Team Cherry");
        game2.setQuantity(70);

        gameRepository.save(game2);

        List<Game> foundGames = gameRepository.findGameByTitle(game.getTitle());

        // only one game has hollow knight title
        assertEquals(1, foundGames.size());

        // assigning game1 to a game object
        Game foundGame = foundGames.get(0);

        // making sure they are equal
        assertEquals(foundGame, game);
    }

    /* testing search by esrb */
    @Test
    public void shouldGetAllGamesByESRB() {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        gameRepository.save(game);

        Game game2 = new Game();
        game2.setTitle("New Team Cherry Game");
        game2.setEsrbRating("E10+");
        game2.setDescription("Video game by Team Cherry");
        game2.setPrice(new BigDecimal("60.00"));
        game2.setStudio("Team Cherry");
        game2.setQuantity(70);

        gameRepository.save(game2);

        List<Game> foundGames = gameRepository.findAllGamesByEsrbRating(game.getEsrbRating());

        // added two games with E10+
        assertEquals(2, foundGames.size());

        // assigning the games to a game object
        Game foundGame = foundGames.get(0);
        Game foundGame2 = foundGames.get(1);

        // making sure they are equal
        assertEquals(foundGame, game);
        assertEquals(foundGame2, game2);
    }

}