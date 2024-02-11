package md.voil.api.Application.Service.Appointment.Validations;

import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Expection.ValidationException;
import md.voil.api.Domain.Interface.Appointment.IValidationAppointment;
import md.voil.api.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDoctorIsAvailable implements IValidationAppointment {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public ValidationDoctorIsAvailable(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void check(AppointmentRegister appointment) {
        boolean alreadyHasAppointment = appointmentRepository.existsByDoctorIdAndDate(appointment.getIdDoctor(), appointment.getDate());

        if(alreadyHasAppointment){
            throw new ValidationException("The doctor already have a appointment in this date");
        }
    }
}
