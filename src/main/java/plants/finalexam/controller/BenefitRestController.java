package plants.finalexam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import plants.finalexam.Service.BenefitService;
import plants.finalexam.dto.BenefitDTO;
// import plants.finalexam.model.Benefit;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/benefits")
public class BenefitRestController {
    
    @Autowired
    private BenefitService benefitService;

    @GetMapping
    public ResponseEntity<List<BenefitDTO>> index(){
        return new ResponseEntity<>(benefitService.findAllBenefitDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenefitDTO> show(@PathVariable Integer id){

        Optional<BenefitDTO> benefitAttempt = benefitService.findBenefitDTOById(id);
        if(benefitAttempt.isEmpty()){
            return new ResponseEntity<BenefitDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<BenefitDTO>(benefitAttempt.get(),HttpStatus.OK );
    }

    // @PostMapping("/create")
    // public ResponseEntity<Benefit> store(@RequestBody Benefit benefit){
    //     return new ResponseEntity<Benefit>(benefitService.create(benefit), HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Benefit> update(@RequestBody Benefit benefit, @PathVariable Integer id){
    //     if(benefitService.findById(id).isEmpty()){
    //         return new ResponseEntity<Benefit>(HttpStatus.NOT_FOUND);
    //     }
    //     return new ResponseEntity<Benefit>(benefitService.update(benefit) ,HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Benefit> delete(@PathVariable Integer id){
    //     if(benefitService.findById(id).isEmpty()){
    //         return new ResponseEntity<Benefit>(HttpStatus.NOT_FOUND);
    //     }
    //     benefitService.deleteById(id);
    //      return new ResponseEntity<Benefit>(HttpStatus.NO_CONTENT);
    // }
}
