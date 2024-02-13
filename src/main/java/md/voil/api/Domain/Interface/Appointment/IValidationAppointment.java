package md.voil.api.Domain.Interface.Appointment;

import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;

public interface IValidationAppointment {
    void check(AppointmentRegister appointment);
}
