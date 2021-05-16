package com.ps.credit.card;

import com.ps.credit.card.repository.CreditCardRepository;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CreditCardProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    protected CreditCardRepository creditCardRepository;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @PostConstruct
    public void mockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected MockMvc mvc() {
        return mockMvc;
    }

    protected void clearDB() {
        creditCardRepository.deleteAllInBatch();
    }

}
