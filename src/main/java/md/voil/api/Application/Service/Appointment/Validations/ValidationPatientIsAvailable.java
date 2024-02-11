package md.voil.api.Application.Service.Appointment.Validations;

import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Expection.ValidationException;
import md.voil.api.Domain.Interface.Appointment.IValidationAppointment;
import md.voil.api.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ValidationPatientIsAvailable implements IValidationAppointment {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public ValidationPatientIsAvailable(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void check(AppointmentRegister appointment) {
        LocalDate date = appointment.getDate().toLocalDate();
        boolean alreadyHasAppointment = appointmentRepository.existsByPatientIdAndDateBetween(appointment.getIdPatient(), date);

        if(alreadyHasAppointment){
            throw new ValidationException("The patient already have a appointment in this date");
        }
    }
}
