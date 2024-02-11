package md.voil.api.Service.Services.Patient;

import md.voil.api.Repository.Domain.Patient.Patient;
import md.voil.api.Repository.Repositories.PatientRepository;
import md.voil.api.Service.DTOs.Patient.PatientDetailingResponse;
import md.voil.api.Service.DTOs.Patient.PatientRegister;
import md.voil.api.Service.DTOs.Patient.PatientSearch;
import md.voil.api.Service.DTOs.Patient.PatientUpdate;
import md.voil.api.Service.Services.Patient.Interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public PatientDetailingResponse Create(PatientRegister register){

        try{
            String zipcodeFormated = register.getAddress().getZipcode().replaceAll("[^0-9]", "");
            register.getAddress().setZipcode(zipcodeFormated);
            Patient entity = new Patient(register);
            repository.save(entity);

            return new PatientDetailingResponse(entity);
        }
        catch (Exception ex){

            throw ex;
        }
    }

    @Override
    public Page<PatientSearch> ReadAll(Pageable pageable){
        try{
            return repository.findAllByActiveTrue(pageable).map(PatientSearch::new);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public PatientDetailingResponse FindById(int id){
        try{
            Patient entity = repository.getReferenceById(id);

            return new PatientDetailingResponse(entity);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    @Transactional
    public PatientDetailingResponse Update(PatientUpdate patient){
        try{
            Patient entity = repository.getReferenceById(patient.getId());
            entity.Update(patient);

            return new PatientDetailingResponse(entity);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    @Transactional
    public  void Delete(int id){
        try{
            Patient patient = repository.getReferenceById(id);
            patient.disableRegister();
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
