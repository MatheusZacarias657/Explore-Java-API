package md.voil.api.Controllers;

import md.voil.api.Domain.DTO.Appointment.AppointmentDetailingResponse;
import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Entity.Doctor.Specialty;
import md.voil.api.Domain.Interface.Appointment.IAppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    private MockMvc mvc;
    private JacksonTester<AppointmentRegister> appointmentRegisterJacksonTester;
    private JacksonTester<AppointmentDetailingResponse> appointmentDetailingResponseJacksonTester;

    @MockBean
    private IAppointmentService appointmentService;

    @Autowired
    AppointmentControllerTest(MockMvc mvc, JacksonTester<AppointmentRegister> appointmentRegisterJacksonTester, JacksonTester<AppointmentDetailingResponse> appointmentDetailingResponseJacksonTester) {
        this.mvc = mvc;
        this.appointmentRegisterJacksonTester = appointmentRegisterJacksonTester;
        this.appointmentDetailingResponseJacksonTester = appointmentDetailingResponseJacksonTester;
    }

    @Test
    @DisplayName("When Body is Empty")
    @WithMockUser
    void CreateTestOne() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/Appointment")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("When Body respect all bean validations")
    @WithMockUser
    void CreateTestTwo() throws Exception {

        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Specialty specialty = Specialty.cardiologia;
        String appointment = appointmentRegisterJacksonTester.write(new AppointmentRegister(2,5, date, specialty)).getJson();
        AppointmentDetailingResponse appointmentDetailingResponse = new AppointmentDetailingResponse(0, 2, 5, date);
        String appointmentDetails = appointmentDetailingResponseJacksonTester.write(appointmentDetailingResponse).getJson();
        when(appointmentService.ScheduleAppointment(any())).thenReturn(appointmentDetailingResponse);

        MockHttpServletResponse response = mvc.perform(post("/Appointment").contentType(MediaType.APPLICATION_JSON).content(appointment)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(appointmentDetails);
    }
}