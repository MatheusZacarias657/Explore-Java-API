package md.voil.api.Service.Services.Patient.Interfaces;

import md.voil.api.Service.DTOs.Patient.PatientDetailingResponse;
import md.voil.api.Service.DTOs.Patient.PatientRegister;
import md.voil.api.Service.DTOs.Patient.PatientSearch;
import md.voil.api.Service.DTOs.Patient.PatientUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IPatientService {
    @Transactional
    PatientDetailingResponse Create(PatientRegister register);

    Page<PatientSearch> ReadAll(Pageable pageable);

    PatientDetailingResponse FindById(int id);

    @Transactional
    PatientDetailingResponse Update(PatientUpdate patient);

    @Transactional
    void Delete(int id);
}
