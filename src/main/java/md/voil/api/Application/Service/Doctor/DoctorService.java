package md.voil.api.Application.Service.Doctor;

import md.voil.api.Domain.DTO.Doctors.DoctorRegister;
import md.voil.api.Domain.DTO.Doctors.DoctorSearch;
import md.voil.api.Domain.DTO.Doctors.DoctorUpdate;
import md.voil.api.Domain.DTO.Doctors.DoctorDetailingResponse;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Repository.DoctorRepository;
import md.voil.api.Domain.Interface.Doctor.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public DoctorDetailingResponse Create(DoctorRegister doctorRegister){

        try{
            String zipcodeFormated = doctorRegister.getAddress().getZipcode().replaceAll("[^0-9]", "");
            doctorRegister.getAddress().setZipcode(zipcodeFormated);
            Doctor entity = new Doctor(doctorRegister);
            repository.save(entity);

            return new DoctorDetailingResponse(entity);
        }
        catch (Exception ex){

            throw ex;
        }
    }

    public Page<DoctorSearch> ReadAll(Pageable pageable){
        try{
            return repository.findAllByActiveTrue(pageable).map(DoctorSearch::new);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public DoctorDetailingResponse FindById(int id){
        try{
            Doctor entity = repository.getReferenceById(id);

            return new DoctorDetailingResponse(entity);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Transactional
    public DoctorDetailingResponse Update(DoctorUpdate doctor){
        try{
            Doctor entity = repository.getReferenceById(doctor.getId());
            entity.Update(doctor);

            return new DoctorDetailingResponse(entity);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Transactional
    public  void Delete(int id){
        try{
            Doctor doctor = repository.getReferenceById(id);
            doctor.disableRegister();
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
