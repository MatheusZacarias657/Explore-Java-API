package md.voil.api.Domain.Interface.Patient;

import md.voil.api.Domain.DTO.Patient.PatientDetailingResponse;
import md.voil.api.Domain.DTO.Patient.PatientRegister;
import md.voil.api.Domain.DTO.Patient.PatientSearch;
import md.voil.api.Domain.DTO.Patient.PatientUpdate;
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
