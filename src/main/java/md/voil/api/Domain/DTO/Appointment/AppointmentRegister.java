package md.voil.api.Domain.DTO.Appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.Entity.Doctor.Specialty;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRegister {

    private int idDoctor;

    @NotNull
    private int idPatient;

    @NotNull
    @Future
    private LocalDateTime date;
    private Specialty specialty;
}
