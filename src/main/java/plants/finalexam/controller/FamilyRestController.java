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


import plants.finalexam.Service.FamilyService;
import plants.finalexam.dto.FamilyDTO;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/families")
public class FamilyRestController {
    
    @Autowired
    private FamilyService familyService;

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> index(){
        return new ResponseEntity<>(familyService.findAllFamilyDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> show(@PathVariable Integer id){

        Optional<FamilyDTO> familyAttempt = familyService.findFamilyDTOById(id);
        if(familyAttempt.isEmpty()){
            return new ResponseEntity<FamilyDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<FamilyDTO>(familyAttempt.get(),HttpStatus.OK );
    }

}
