package com.company.gamestore.repository;

import com.company.gamestore.model.SalesTaxRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRepositoryTest {
    @Autowired
    SalesTaxRepository salesTaxRepository;

    @Before
    public void setUp() throws Exception {
        salesTaxRepository.deleteAll();
    }

    @Test
    public void findSalesTaxByState() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setRate(new BigDecimal("0.15"));
        salesTaxRate.setState("VA");
        salesTaxRate = salesTaxRepository.save(salesTaxRate);

        Optional<SalesTaxRate> salesTaxRate1 = salesTaxRepository.findByState("VA");
        assertEquals(salesTaxRate1.get(), salesTaxRate);
    }
}
