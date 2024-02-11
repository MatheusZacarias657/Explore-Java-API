package md.voil.api.Domain.Interface.Appointment;

import md.voil.api.Domain.DTO.Appointment.AppointmentDetailingResponse;
import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;

public interface IAppointmentService {
    AppointmentDetailingResponse ScheduleAppointment(AppointmentRegister appointment);
}
