package tn.esprit.pi_backend.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi_backend.entities.Formation;
import tn.esprit.pi_backend.service.IEmailService;
import tn.esprit.pi_backend.service.IFormationService;


import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:4200","http://localhost:4200"})
@RestController
@RequestMapping("/formations")
public class FormationController {
    @Autowired
    private IFormationService formationService;
    @Autowired
    private IEmailService emailService;

    @PostMapping
    public Formation createFormation(@RequestBody Formation formation) {
        //return formationService.createFormation(formation);
        Formation createdFormation = formationService.createFormation(formation);

        // Send email
        String emailAddress = "wajdi.hassyaoui@esprit.tn";
        emailService.sendFormationAddedEmail(emailAddress);

        return createdFormation;
    }

    @GetMapping("/{id}")
    public Formation getFormationById(@PathVariable Long id) {
        return formationService.getFormationById(id);
    }

    @GetMapping
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @PutMapping("/{id}")
    public Formation updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
        Formation existingFormation = formationService.getFormationById(id);
        if (existingFormation != null) {
            existingFormation.setFormationName(formation.getFormationName());
            existingFormation.setFormationStatus(formation.getFormationStatus());
            existingFormation.setDateStart(formation.getDateStart());
            existingFormation.setDateFinish(formation.getDateFinish());
            return formationService.updateFormation(existingFormation);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
    }
    @GetMapping("/search/{formationName}")
    public ResponseEntity<List<Formation>> getFormationsByName(@PathVariable String formationName) {
        List<Formation> formations = formationService.getFormationsByName(formationName);
        return ResponseEntity.ok(formations);
    }
}