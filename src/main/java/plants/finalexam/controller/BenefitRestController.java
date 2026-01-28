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

}
