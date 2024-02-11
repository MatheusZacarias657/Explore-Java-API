package md.voil.api.Application.Service.Appointment.Validations;

import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Expection.ValidationException;
import md.voil.api.Domain.Interface.Appointment.IValidationAppointment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidationOutOfWorkHours implements IValidationAppointment {
    @Override
    public void check(AppointmentRegister appointment) {

        LocalDateTime appointmentDate = appointment.getDate();
        boolean isSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isBeforeOpen = appointmentDate.getHour() < 7;
        boolean isAfterClose = appointmentDate.getHour() > 18;

        if(isSunday || isBeforeOpen || isAfterClose){
            throw new ValidationException("The appointment date is out of work hours");
        }
    }
}
