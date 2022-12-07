package com.company.gamestore.repository;

import com.company.gamestore.model.ProcessingFee;
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
public class ProcessingFeeRepositoryTest {
    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @Before
    public void setUp() throws Exception {
        processingFeeRepository.deleteAll();
    }

    @Test
    public void findProcessingFeeByProductType() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.49"));
        processingFee.setProductType("Game");
        processingFee = processingFeeRepository.save(processingFee);

        Optional<ProcessingFee> processingFee1 = processingFeeRepository.findByProductType("Game");
        assertEquals(processingFee1.get(), processingFee);
    }
}
