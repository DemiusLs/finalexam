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

import plants.finalexam.Service.ControindicationService;
import plants.finalexam.dto.ControindicationDTO;
import plants.finalexam.model.Controindication;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/controindications")
public class ControindicationRestController {
    
    @Autowired
    private ControindicationService controindicationService;

    @GetMapping
    public ResponseEntity<List<Controindication>> index(){
        return new ResponseEntity<>(controindicationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControindicationDTO> show(@PathVariable Integer id){

        Optional<ControindicationDTO> controindicationAttempt = controindicationService.findControindicationDTOById(id);
        if(controindicationAttempt.isEmpty()){
            return new ResponseEntity<ControindicationDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ControindicationDTO>(controindicationAttempt.get(),HttpStatus.OK );
    }

    @PostMapping("/create")
    public ResponseEntity<Controindication> store(@RequestBody Controindication controindication){
        return new ResponseEntity<Controindication>(controindicationService.create(controindication), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Controindication> update(@RequestBody Controindication controindication, @PathVariable Integer id){
        if(controindicationService.findById(id).isEmpty()){
            return new ResponseEntity<Controindication>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Controindication>(controindicationService.update(controindication) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Controindication> delete(@PathVariable Integer id){
        if(controindicationService.findById(id).isEmpty()){
            return new ResponseEntity<Controindication>(HttpStatus.NOT_FOUND);
        }
        controindicationService.deleteById(id);
         return new ResponseEntity<Controindication>(HttpStatus.NO_CONTENT);
    }
}
