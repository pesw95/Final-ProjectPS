package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;


    // GET methods

    // get game by id
    @GetMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getBookById(@PathVariable int id) {
        Optional<Game> foundGame = gameRepository.findById(id);
        return foundGame.orElse(null);
    }

    // get all games
    @GetMapping(value = "/game")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // get all games by studio
    @GetMapping(value = "/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGamesByStudio(@PathVariable String studio) {
        return gameRepository.findAllGamesByStudio(studio);
    }

    // get all games by esrb
    @GetMapping(value = "/game/rating/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGamesByEsrb(@PathVariable String esrb) {
        return gameRepository.findAllGamesByEsrbRating(esrb);
    }

    // get all games by title
    @GetMapping(value = "/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGameByTitle(@PathVariable String title) {
        List<Game> foundGames = gameRepository.findGameByTitle(title);
        return foundGames;
    }

    // POST methods

    // create game
    @PostMapping(value = "/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        return gameRepository.save(game);
    }



    // PUT methods

    // update game
    @PutMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Game updateGameById(@RequestBody Game game, @PathVariable int id) {

        Game updatedGame = gameRepository.findById(id)
                .map(g -> {
                    g.setTitle(game.getTitle());
                    g.setEsrbRating(game.getEsrbRating());
                    g.setDescription(game.getDescription());
                    g.setPrice(game.getPrice());
                    g.setStudio(game.getStudio());
                    g.setQuantity(game.getQuantity());
                    return gameRepository.save(g);
                })
                .orElseGet(() -> {
                    game.setGameId(id);
                    return gameRepository.save(game); // if it is not found then save the updated book
                });
        return updatedGame;
    }

    // DELETE methods

    // delete game by id
    @DeleteMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameById(@PathVariable int id) {
        gameRepository.deleteById(id);
    }

}
