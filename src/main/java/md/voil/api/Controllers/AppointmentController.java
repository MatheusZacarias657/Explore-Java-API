package md.voil.api.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Interface.Appointment.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    private IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @PostMapping
    public ResponseEntity<Object> Create(@RequestBody @Valid AppointmentRegister appointment){
        return ResponseEntity.ok(appointmentService.ScheduleAppointment(appointment));
    }
}
