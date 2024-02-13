package md.voil.api.Application.Service.Appointment;

import jakarta.transaction.Transactional;
import md.voil.api.Domain.DTO.Appointment.AppointmentDetailingResponse;
import md.voil.api.Domain.DTO.Appointment.AppointmentRegister;
import md.voil.api.Domain.Entity.Appointment.Appointment;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Patient.Patient;
import md.voil.api.Domain.Expection.ValidationException;
import md.voil.api.Domain.Interface.Appointment.IAppointmentService;
import md.voil.api.Domain.Interface.Appointment.IValidationAppointment;
import md.voil.api.Repository.AppointmentRepository;
import md.voil.api.Repository.DoctorRepository;
import md.voil.api.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final List<IValidationAppointment> validationAppointment;

    @Autowired
    public AppointmentService(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, List<IValidationAppointment> validationAppointment) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.validationAppointment = validationAppointment;
    }

    @Override
    @Transactional
    public AppointmentDetailingResponse ScheduleAppointment(AppointmentRegister appointment){

        if(!patientRepository.findActiveById(appointment.getIdPatient())){
            throw new ValidationException("Patient doesn't exists");
        }

        if(appointment.getIdDoctor() != 0){
            if(!doctorRepository.findActiveById(appointment.getIdDoctor())){
                throw new ValidationException("Doctor doesn't exists");
            }
        }

        appointment.setDate(appointment.getDate().withSecond(0).withNano(0));

        validationAppointment.forEach(v -> v.check(appointment));

        Patient patient = patientRepository.getReferenceById(appointment.getIdPatient());
        Doctor doctor = chooseDoctor(appointment);

        if(doctor == null){
            throw new ValidationException("Doesn't exist doctors available in this date");
        }

        Appointment entity = new Appointment(0, doctor, patient, appointment.getDate());
        appointmentRepository.save(entity);

        return new AppointmentDetailingResponse(entity);
    }

    private Doctor chooseDoctor(AppointmentRegister appointment){

        if(appointment.getIdDoctor() != 0){
            return doctorRepository.getReferenceById(appointment.getIdDoctor());
        }

        if(appointment.getSpecialty() == null){
            throw  new ValidationException("Specialty is mandatory when doctor is null");
        }

        return doctorRepository.chooseAvailableRandomDoctor(appointment.getSpecialty(), appointment.getDate());
    }
}
