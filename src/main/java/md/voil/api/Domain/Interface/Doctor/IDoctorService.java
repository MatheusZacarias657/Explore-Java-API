package md.voil.api.Domain.Interface.Doctor;

import md.voil.api.Domain.DTO.Doctors.DoctorRegister;
import md.voil.api.Domain.DTO.Doctors.DoctorSearch;
import md.voil.api.Domain.DTO.Doctors.DoctorUpdate;
import md.voil.api.Domain.DTO.Doctors.DoctorDetailingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDoctorService {
    DoctorDetailingResponse Create(DoctorRegister doctorRegister);
    Page<DoctorSearch> ReadAll(Pageable pageable);
    DoctorDetailingResponse FindById(int id);
    DoctorDetailingResponse Update(DoctorUpdate doctor);
    void Delete(int id);
}
