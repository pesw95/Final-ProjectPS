package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
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
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void addGetDeleteConsole() {
        Console console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS-5");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("499.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(29);

        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());

        assertEquals(console1.get(), console);

        consoleRepository.deleteById(console.getId());

        console1 = consoleRepository.findById(console.getId());

        assertFalse(console1.isPresent());
    }


    @Test
    public void getAllConsoles() {
        Console console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS-5");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("499.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(29);
        console = consoleRepository.save(console);

        console = new Console();
        console.setManufacturer("Toshiba");
        console.setModel("Kumali");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("377.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(30);
        console = consoleRepository.save(console);

        List<Console> aList = consoleRepository.findAll();

        assertEquals(aList.size(), 2);
    }

    @Test
    public void findByManufacturer() {
        Console console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS-5");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("499.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(29);
        console = consoleRepository.save(console);

        console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS-4");
        console.setMemoryAmount("2GB RAM");
        console.setPrice(new BigDecimal("399.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(60);
        console = consoleRepository.save(console);

        List<Console> consoles = consoleRepository.findByManufacturer("Sony");
        assertEquals(consoles.size(), 2);
    }

    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS-5");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("499.95"));
        console.setProcessor("Qualcomm SnapDragon");
        console.setQuantity(29);
        console = consoleRepository.save(console);

        console.setModel("PS-4");
        console.setMemoryAmount("4GB RAM");
        console.setPrice(new BigDecimal("699.95"));
        consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());
        assertEquals(console1.get(), console);
    }

}