package com.api.itmanager;

import com.api.itmanager.modules.client.controller.ClientController;
import com.api.itmanager.modules.client.dto.ClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestPropertySource(locations="classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientControllerTest extends ApiItmanagerApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientController clientController;

    private static ClientRequest request;

    @BeforeAll
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    private void createClientDTOMock(String cnpj) {
        request = new ClientRequest();
        request.setName("Cliente Teste Service 1");
        request.setCnpj(cnpj);
        request.setAddress("Avenida Cliente Teste Service, n°1234, Bairro Teste Service, Cidade Teste Service");
    }

    @Test
    public void testListAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client")).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testCreateClient1() throws Exception {
        this.createClientDTOMock("12345678000199");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/client")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateClient2() throws Exception {
        this.createClientDTOMock("12345678000199");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/client")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
/*
    @Test
    public void testFindByIdIfClientExists() throws Exception {
        this.createClientDTOMock("12.123.555/0001-99");
        clientController.createClient(clientDTOMock);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindByIdIfClientNotExists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client/25")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateClient() throws Exception{
        this.createClientDTOMock("85.159.351/0002-15");
        clientController.createClient(clientDTOMock);
        clientDTOMock.setName("Novo Nome Teste");
        clientDTOMock.setCnpj("15.159.351/0002-15");
        clientDTOMock.setAddress("Novo avenida dos Testes, 123");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/client/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientDTOMock)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateClientWithError() throws Exception{
        this.createClientDTOMock("85.999.351/0003-15");
        clientController.createClient(clientDTOMock);
        clientDTOMock.setName("Novo Nome Teste");
        clientDTOMock.setCnpj("15.159.351/0002-15123456");
        clientDTOMock.setAddress("Novo avenida dos Testes, 123");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/client/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientDTOMock)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testDeleteByIdClientExists() throws Exception {
        this.createClientDTOMock("84.813.917/0002-29");
        clientController.createClient(clientDTOMock);

        this.createClientDTOMock("12.817.825/0001-59");
        clientController.createClient(clientDTOMock);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/client/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientDTOMock)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteByIdClientNotExists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/client/25")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientDTOMock)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

 */
}