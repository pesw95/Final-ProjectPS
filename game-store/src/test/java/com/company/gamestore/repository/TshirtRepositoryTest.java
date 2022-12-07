package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtRepositoryTest {

    @Autowired
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    @Test
    public void addGetDeleteTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getId());

        assertEquals(tshirt1.get(),tshirt);

        tshirtRepository.deleteById(tshirt.getId());

        tshirt1 = tshirtRepository.findById(tshirt.getId());

        assertFalse(tshirt1.isPresent());

    }
    @Test
    public void addTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getId());

        assertEquals(tshirt1.get(),tshirt);
    }

    @Test
    public void updateTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        tshirt = tshirtRepository.save(tshirt);

        tshirt.setSize("L");
        tshirt.setColor("Blue");

        tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getId());

        assertEquals(tshirt1.get(),tshirt);

    }
    @Test
    public void deleteTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getId());

        tshirtRepository.deleteById(tshirt.getId());

        assertEquals(tshirt1.get(),tshirt);
    }
    @Test
    public void getAllTshirts(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);

        tshirt = tshirtRepository.save(tshirt);

        tshirt = new Tshirt();
        tshirt.setColor("Blue");
        tshirt.setSize("Large");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        tshirt = tshirtRepository.save(tshirt);

        List<Tshirt> tshirts = tshirtRepository.findAll();
        assertEquals(tshirts.size(), 2);

    }

    @Test
    public void getShirtByColor(){
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setSize("M");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        tshirt = tshirtRepository.save(tshirt);

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("Blue");
        tshirt1.setSize("L");
        tshirt1.setDescription("100% Organic Cotton");
        tshirt1.setPrice(new BigDecimal("34.99"));
        tshirt1.setQuantity(45);
        tshirt.setId(2);

        tshirt1 = tshirtRepository.save(tshirt);

        List<Tshirt> tshirt2 = tshirtRepository.findByColor(tshirt.getColor());

        assertEquals(tshirt2.size(),2);


    }


    @Test
    public void getTshirtBySize(){
        Tshirt tshirt = new Tshirt();

        tshirt.setColor("Red");
        tshirt.setSize("Medium");
        tshirt.setDescription("100% Organic Cotton");
        tshirt.setPrice(new BigDecimal("34.99"));
        tshirt.setQuantity(45);
        tshirt.setId(4);

        tshirt = tshirtRepository.save(tshirt);


        Tshirt tshirt1 = new Tshirt();

        tshirt1.setColor("Blue");
        tshirt1.setSize("Large");
        tshirt1.setDescription("100% Organic Cotton");
        tshirt1.setPrice(new BigDecimal("34.99"));
        tshirt1.setQuantity(45);
        tshirt.setId(2);

        tshirt1 = tshirtRepository.save(tshirt);

        List<Tshirt> tshirts = tshirtRepository.findBySize(tshirt.getSize());

        assertEquals(tshirts.size(), 2);



    }




}