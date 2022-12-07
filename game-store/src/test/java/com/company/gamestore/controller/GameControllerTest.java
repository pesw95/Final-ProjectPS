package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest({GameController.class})
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
    }

    // testing get all games route works
    @Test
    public void shouldReturnAllGames() throws Exception {
        mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // testing get by route id works
    @Test
    public void shouldReturnGame() throws Exception {
        mockMvc.perform(get("/game/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // testing post route works
    @Test
    public void shouldCreateAndReturnNewGame() throws Exception {

        // arrange
        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        // object to json
        String inputJson = mapper.writeValueAsString(game);

        // act
        mockMvc.perform(
                        post("/game")                            // post
                                .content(inputJson)                       // request body
                                .contentType(MediaType.APPLICATION_JSON)  // json format
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())                                // print to console
                .andExpect(status().isCreated());              // status 201
    }

    // testing update game route works
    @Test
    public void shouldUpdate() throws Exception {

        Game game = new Game();
        game.setTitle("Hollow Knight");
        game.setEsrbRating("E10+");
        game.setDescription("Hollow Knight is a 2017 Metroidvania video game developed and published by Team Cherry");
        game.setPrice(new BigDecimal("19.99"));
        game.setStudio("Team Cherry");
        game.setQuantity(70);

        // object to json
        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(put("/game/2")
                .content(inputJson)                       // request body
                .contentType(MediaType.APPLICATION_JSON)  // json format
        )
                .andDo(print())                                // print to console
                .andExpect(status().isAccepted());
    }

    // testing get by title route works
    @Test
    public void shouldReturnGameByTitle() throws Exception {
        mockMvc.perform(get("/game/title/Hollow Knight"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // testing get by studio route works
    @Test
    public void shouldReturnGameByStudio() throws Exception {
        mockMvc.perform(get("/game/studio/Team Cherry"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // testing get by esrb route works
    @Test
    public void shouldReturnGameByRating() throws Exception {
        mockMvc.perform(get("/game/rating/E10+"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}