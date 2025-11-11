package plants.finalexam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import plants.finalexam.Service.FamilyService;
import plants.finalexam.model.Family;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/families")
public class FamilyRestController {
    
    @Autowired
    private FamilyService familyService;

    @GetMapping
    public ResponseEntity<List<Family>> index(){
        return new ResponseEntity<>(familyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Family> show(@PathVariable Integer id){

        Optional<Family> familyAttempt = familyService.findById(id);
        if(familyAttempt.isEmpty()){
            return new ResponseEntity<Family>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Family>(familyAttempt.get(),HttpStatus.OK );
    }

    @PostMapping("/create")
    public ResponseEntity<Family> store(@RequestBody Family family){
        return new ResponseEntity<Family>(familyService.create(family), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Family> update(@RequestBody Family family, @PathVariable Integer id){
        if(familyService.findById(id).isEmpty()){
            return new ResponseEntity<Family>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Family>(familyService.update(family) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Family> delete(@PathVariable Integer id){
        if(familyService.findById(id).isEmpty()){
            return new ResponseEntity<Family>(HttpStatus.NOT_FOUND);
        }
        familyService.deleteById(id);
         return new ResponseEntity<Family>(HttpStatus.NO_CONTENT);
    }
}
