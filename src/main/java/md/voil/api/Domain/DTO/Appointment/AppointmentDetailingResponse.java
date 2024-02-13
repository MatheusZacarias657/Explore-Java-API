package md.voil.api.Domain.DTO.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.voil.api.Domain.Entity.Appointment.Appointment;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentDetailingResponse {

    private int id;
    private int idDoctor;
    private int idPatient;
    private LocalDateTime date;

    public AppointmentDetailingResponse(Appointment appointment){
        this.id = appointment.getId();
        this.idDoctor = appointment.getDoctor().getId();
        this.idPatient = appointment.getPatient().getId();
        this.date = appointment.getDate();
    }
}
