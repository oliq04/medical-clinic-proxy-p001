package org.example.medicalclinicproxyp001.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import tools.jackson.databind.ObjectMapper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@EnableWireMock(@ConfigureWireMock(
        port = 9997
))
public class MedicalProxyFeignTest {

    @Autowired
    private MedicalClinicFeignClient medicalClinicFeignClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void patientVisits_CorrectData_PageOfVisitsReturned() throws Exception {
        stubFor(get(urlPathEqualTo("/visit/patient/1"))
                .withQueryParam("page", equalTo("0"))
                .withQueryParam("size", equalTo("5"))
                .willReturn(aResponse()
                .withBodyFile("page_of_visits.json")
                .withHeader("Content-Type", "application/json")));

        mockMvc.perform(MockMvcRequestBuilders.get("/visit/patient/{id}", "1")
                        .param("page","0")
                        .param("size","5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.pageSize").value(5))
                .andExpect(jsonPath("$.currentPage").value(0))
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].startDate").value("2027-08-18T18:15:00"))
                .andExpect(jsonPath("$.content[0].endDate").value("2027-08-18T19:30:00"))
                .andExpect(jsonPath("$.content[0].patient.id").value(1))
                .andExpect(jsonPath("$.content[0].patient.idCardNo").value(2004))
                .andExpect(jsonPath("$.content[0].patient.firstName").value("Grzegorz"))
                .andExpect(jsonPath("$.content[0].patient.lastName").value("Zysk"))
                .andExpect(jsonPath("$.content[0].patient.phoneNumber").value("+444444"))
                .andExpect(jsonPath("$.content[0].patient.birthday").value("2004-12-03T00:00:00"))
                .andExpect(jsonPath("$.content[0].doctor.id").value(1))
                .andExpect(jsonPath("$.content[0].doctor.firstName").value("Ge332334"))
                .andExpect(jsonPath("$.content[0].doctor.lastName").value("Doctor"))
                .andExpect(jsonPath("$.content[0].doctor.specialization").value("CARDIOLOGY"))
                .andExpect(jsonPath("$.content[0].clinic.id").value(1))
                .andExpect(jsonPath("$.content[0].clinic.name").value("Klinika3"))
                .andExpect(jsonPath("$.content[0].clinic.town").value("Warszawa"))
                .andExpect(jsonPath("$.content[0].clinic.postCode").value("11-223"))
                .andExpect(jsonPath("$.content[0].clinic.address").value("warszawska"));
    }
}
