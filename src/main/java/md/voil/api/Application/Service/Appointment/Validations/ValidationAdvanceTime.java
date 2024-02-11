package md.voil.api.Application.Service.Appointment.Validations;

import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Expection.ValidationException;
import md.voil.api.Domain.Interface.Appointment.IValidationAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationAdvanceTime implements IValidationAppointment {

    @Override
    public void check(AppointmentRegister appointment) {

        LocalDateTime appointmentDate = appointment.getDate();
        LocalDateTime now = LocalDateTime.now();
        var diferenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (diferenceInMinutes < 30) {
            throw new ValidationException("The appointment has less of 30 minutes of Advance");
        }
    }
}
