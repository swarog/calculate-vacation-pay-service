package org.swarog.calculateVacationPay.resources

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationResourceTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc;

    @Test
    fun authenticateCall() {
        mockMvc.perform(get("/authenticate"))
                .andExpect(status().isOk)
                .andExpect(content().string("hello world"))
    }
}
