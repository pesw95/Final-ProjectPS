package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
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
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();
    Console console = new Console();

    private List<Console> consoleList;

    @Before
    public void Setup() throws Exception{
        console.setManufacturer("Sony");
        console.setModel("PS-5");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("499.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(29);
    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        console.setId(2);
        String outputJson = mapper.writeValueAsString(console);

        mockMvc.perform(get("/consoles/2").content(outputJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllConsoles() throws Exception{
        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(consoleList);

        // ACT
        mockMvc.perform(get("/consoles").content(outputJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception{
        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(consoleList);

        // ACT
        mockMvc.perform(get("/consoles/manufacturer/Sony").content(outputJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    @Test
    public void shouldAddConsole() throws Exception{
        // ARRANGE
//        Console console = new Console();
//        console.setManufacturer("Sony");
//        console.setModel("PS-5");
//        console.setMemoryAmount("3GB RAM");
//        console.setPrice(new BigDecimal("499.95"));
//        console.setProcessor("Qualcomm SnapDragon");
//        console.setQuantity(29);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(console);

        // ACT
        mockMvc.perform(
                        post("/consoles")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }

    @Test
    public void shouldUpdateConsole() throws Exception{
//        Console console = new Console();
//        console.setManufacturer("Sony");
//        console.setModel("PS-5");
//        console.setMemoryAmount("3GB RAM");
//        console.setPrice(new BigDecimal("499.95"));
//        console.setProcessor("Qualcomm SnapDragon");
//        console.setQuantity(29);
        console.setId(2);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        put("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteConsole() throws Exception{
        mockMvc.perform(delete("/consoles/5"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}