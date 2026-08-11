package br.com.joaofelipefaria.cartoes.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;
import br.com.joaofelipefaria.cartoes.api.dto.TransacaoRequest;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CartoesApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCard() throws Exception {

        var request = new CriarCartaoRequest(
                "123-123-123-123",
                "1234"
        );

        mockMvc.perform(
                post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
        .andExpect(status().isCreated())
        .andExpect(content().json("""
                {
                    "numeroCartao": "123-123-123-123",
                    "senha": "1234"
                }
                """));
    }

    @Test
    void shouldGetCardBalance() throws Exception {

        mockMvc.perform(
                get("/cartoes/123-123")
        )
        .andExpect(status().isOk())
        .andExpect(content().string("0.00"));
    }

    @Test
    void shouldPerformTransaction() throws Exception {
        var request = new TransacaoRequest(
                "123-123",
                "123",
                new java.math.BigDecimal("10.00")
        );

        mockMvc.perform(
                post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
        .andExpect(status().isCreated())
        .andExpect(content().string("OK"));
    }
    
    @Test
    void shouldReturnNotFoundWhenCardExists() throws Exception {
    	mockMvc.perform(get("/cartoes/123-123999"))
    	.andExpect(status().isNotFound())
    	.andExpect(content().string(""));
    }
    
    @Test
    void shouldReturnUnprocessableEntityWhenCardAlreadyExists() throws Exception {
        var request = new CriarCartaoRequest(
                "123-123-124",
                "1234"
        );

        mockMvc.perform(
                post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
        .andExpect(status().isCreated())
        .andExpect(content().json("""
                {
                    "numeroCartao": "123-123-124",
                    "senha": "1234"
                }
                """));;
        
        var requestIdentical = new CriarCartaoRequest(
                "123-123-124",
                "1234"
        );

        mockMvc.perform(
                post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestIdentical))
        )
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().json("""
                {
                    "numeroCartao": "123-123-124",
                    "senha": "1234"
                }
                """));
    }
}