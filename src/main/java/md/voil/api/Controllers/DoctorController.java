package md.voil.api.Controllers;


import jakarta.validation.Valid;
import md.voil.api.Service.DTOs.Doctors.DoctorDetailingResponse;
import md.voil.api.Service.DTOs.Doctors.DoctorRegister;
import md.voil.api.Service.DTOs.Doctors.DoctorUpdate;
import md.voil.api.Service.Services.Doctor.Interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Object> Create(@RequestBody @Valid DoctorRegister doctor, UriComponentsBuilder uriBUilder){

        DoctorDetailingResponse respose = doctorService.Create(doctor);
        URI uri = uriBUilder.path("/Doctor/{id}").buildAndExpand(respose.getId()).toUri();

        return ResponseEntity.created(uri).body(respose);
    }

    @GetMapping
    public ResponseEntity<Object> Read(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){

        return ResponseEntity.ok(doctorService.ReadAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ReadById(@PathVariable int id){

        return ResponseEntity.ok(doctorService.FindById(id));
    }

    @PutMapping
    public ResponseEntity<Object> Update(@RequestBody @Valid DoctorUpdate doctor){

        return ResponseEntity.ok(doctorService.Update(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {

        doctorService.Delete(id);

        return ResponseEntity.noContent().build();
    }
}
