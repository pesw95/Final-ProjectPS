package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TshirtController tshirtController;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of  for testing purposes
    private List<Tshirt> tshirtList;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldGetTshirtById() throws Exception{
        Tshirt tshirt = new Tshirt();

        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        String outputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(get("/tshirts/4"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetAllTshirts() throws Exception{
        Tshirt tshirt = new Tshirt();

        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(tshirtList);

        Tshirt tshirt1 = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("Large");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(6);

        // ACT

        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void shouldAddTshirt() throws Exception{

        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setColor("Red");
        inputTshirt.setSize("Medium");
        inputTshirt.setDescription("100% Organic Cotton");
        inputTshirt.setPrice(new BigDecimal("34.99"));
        inputTshirt.setQuantity(45);

        String inputJson = mapper.writeValueAsString(inputTshirt);

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setColor("Red");
        outputTshirt.setSize("Medium");
        outputTshirt.setDescription("100% Organic Cotton");
        outputTshirt.setPrice(new BigDecimal("34.99"));
        outputTshirt.setQuantity(45);
        outputTshirt.setId(4);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(
                        post("/tshirts")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());          // ASSERT (Status code is 201)


    }

    @Test
    public void shouldDeleteTshirt() throws Exception{
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        String inputJson = mapper.writeValueAsString(tshirtList);

        Tshirt outputTshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        String outputJson = mapper.writeValueAsString(tshirtList);


        mockMvc.perform(
                        delete("/tshirts/4")                            // Perform the delete request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isNoContent());              // ASSERT (status code is 201)

    }
    @Test
    public void shouldUpdateTshirt() throws Exception{

        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setColor("Red");
        inputTshirt.setSize("M");
        inputTshirt.setDescription("100% Organic Cotton");
        inputTshirt.setPrice(new BigDecimal("34.99"));
        inputTshirt.setQuantity(45);

        String inputJson = mapper.writeValueAsString(inputTshirt);

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setColor("Redish");
        outputTshirt.setSize("Medium");
        outputTshirt.setDescription("100% Organic Cotton");
        outputTshirt.setPrice(new BigDecimal("35.99"));
        outputTshirt.setQuantity(45);
        outputTshirt.setId(4);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(
                        put("/tshirts")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isNoContent());              // ASSERT


    }



    @Test
    public void shouldGetTshirtByColor() throws Exception{
        Tshirt tshirt = new Tshirt();

        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        String outputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(get("/tshirts/color/red"))
                .andDo(print())
                .andExpect(status().isOk());


    }


    @Test
    public void shouldGetTshirtBySize() throws Exception{
        Tshirt tshirt = new Tshirt();

        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        String outputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(get("/tshirts/size/medium"))
                .andDo(print())
                .andExpect(status().isOk());


    }

}