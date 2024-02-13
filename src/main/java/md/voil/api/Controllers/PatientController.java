package md.voil.api.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import md.voil.api.Domain.DTO.Patient.PatientDetailingResponse;
import md.voil.api.Domain.DTO.Patient.PatientRegister;
import md.voil.api.Domain.DTO.Patient.PatientUpdate;
import md.voil.api.Domain.Interface.Patient.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/Patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> Create(@RequestBody @Valid PatientRegister patient, UriComponentsBuilder uriBUilder){

        PatientDetailingResponse respose = patientService.Create(patient);
        URI uri = uriBUilder.path("/Doctor/{id}").buildAndExpand(respose.getId()).toUri();

        return ResponseEntity.created(uri).body(respose);
    }

    @GetMapping
    public ResponseEntity<Object> Read(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){

        return ResponseEntity.ok(patientService.ReadAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ReadById(@PathVariable int id){

        return ResponseEntity.ok(patientService.FindById(id));
    }

    @PutMapping
    public ResponseEntity<Object> Update(@RequestBody @Valid PatientUpdate patient){

        return ResponseEntity.ok(patientService.Update(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {

        patientService.Delete(id);

        return ResponseEntity.noContent().build();
    }
}
