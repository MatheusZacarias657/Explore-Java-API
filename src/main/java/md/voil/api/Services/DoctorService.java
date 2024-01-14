package md.voil.api.Services;

import md.voil.api.DTOs.Doctors.DoctorRegister;
import md.voil.api.DTOs.Doctors.DoctorSearch;
import md.voil.api.DTOs.Doctors.DoctorUpdate;
import md.voil.api.Domain.Doctor;
import md.voil.api.Repositories.DoctorRepository;
import md.voil.api.Services.Interfaces.IDoctorService;
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

    @Override
    @Transactional
    public void Create(DoctorRegister doctorRegister){

        try{
            String zipcodeFormated = doctorRegister.getAddress().getZipcode().replaceAll("[^0-9]", "");
            doctorRegister.getAddress().setZipcode(zipcodeFormated);
            repository.save(new Doctor(doctorRegister));
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

    @Transactional
    public void Update(DoctorUpdate doctor){
        try{
            Doctor oldRegister = repository.getReferenceById(doctor.getId());
            oldRegister.Update(doctor);
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
