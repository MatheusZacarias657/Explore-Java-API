package md.voil.api.Controllers;


import jakarta.validation.Valid;
import md.voil.api.DTOs.Doctors.DoctorRegister;
import md.voil.api.DTOs.Doctors.DoctorUpdate;
import md.voil.api.Services.Interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Object> Create(@RequestBody @Valid DoctorRegister doctor){

        try {
            doctorService.Create(doctor);

            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Doctor created successfully");

            return  new ResponseEntity<>(respose, HttpStatus.CREATED);
        }
        catch (Exception ex){

            ex.printStackTrace();
            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Failed to create Doctor");

            return new ResponseEntity<>(respose, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> Read(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        try {
            return  new ResponseEntity<>(doctorService.ReadAll(pageable), HttpStatus.OK);
        }
        catch (Exception ex){

            ex.printStackTrace();
            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Failed to create Doctor");

            return new ResponseEntity<>(respose, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> Update(@RequestBody @Valid DoctorUpdate doctor){

        try {
            doctorService.Update(doctor);

            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Doctor update successfully");

            return  new ResponseEntity<>(respose, HttpStatus.OK);
        }
        catch (Exception ex){

            ex.printStackTrace();
            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Failed to update Doctor");

            return new ResponseEntity<>(respose, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {

        try {
            doctorService.Delete(id);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex) {

            ex.printStackTrace();
            Map<String, Object> respose = new HashMap<>();
            respose.put("message", "Failed to delete Doctor");

            return new ResponseEntity<>(respose, HttpStatus.BAD_REQUEST);
        }
    }
}
