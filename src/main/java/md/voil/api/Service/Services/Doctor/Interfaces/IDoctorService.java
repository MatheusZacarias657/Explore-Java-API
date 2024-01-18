package md.voil.api.Service.Services.Doctor.Interfaces;

import md.voil.api.Service.DTOs.Doctors.DoctorRegister;
import md.voil.api.Service.DTOs.Doctors.DoctorSearch;
import md.voil.api.Service.DTOs.Doctors.DoctorUpdate;
import md.voil.api.Service.DTOs.Doctors.DoctorDetailingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDoctorService {
    DoctorDetailingResponse Create(DoctorRegister doctorRegister);
    Page<DoctorSearch> ReadAll(Pageable pageable);
    DoctorDetailingResponse FindById(int id);
    DoctorDetailingResponse Update(DoctorUpdate doctor);
    void Delete(int id);
}
