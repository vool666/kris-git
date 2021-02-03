package ee.bcs.valiit.tasks.solution.controller;

import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Lesson4ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCustomer() throws Exception {

        http://localhost/8080
        mockMvc.perform(MockMvcRequestBuilders.post("/createCustomer")
                .contentType("application/json").content("{\n" +
                        "  \"customerid\" : 6,\n" +
                        "  \"accountid\" : 6,\n" +
                        "  \"eesnimi\": \"x\",\n" +
                        "  \"perekonnanimi\" : \"y\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createAccount() throws Exception {

        http://localhost/8080
        mockMvc.perform(MockMvcRequestBuilders.post("/createAccount")
                .contentType("application/json").content("{\n" +
                        "  \"customerid\" : 6,\n" +
                        "  \"accountid\" : 6\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}