package md.voil.api.Services.Interfaces;

import md.voil.api.DTOs.Doctors.DoctorRegister;
import md.voil.api.DTOs.Doctors.DoctorSearch;
import md.voil.api.DTOs.Doctors.DoctorUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDoctorService {
    void Create(DoctorRegister doctorRegister);
    Page<DoctorSearch> ReadAll(Pageable pageable);
    void Update(DoctorUpdate doctor);
    void Delete(int id);
}
