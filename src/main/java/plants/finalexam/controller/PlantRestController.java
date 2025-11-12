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

import plants.finalexam.Service.PlantService;
import plants.finalexam.dto.PlantDTO;
import plants.finalexam.model.Plant;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/plants")
public class PlantRestController {
    
    @Autowired
    private PlantService plantService;
    
    @GetMapping
    public ResponseEntity<List<Plant>> index(){
        return new ResponseEntity<>(plantService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantDTO> show(@PathVariable Integer id){
        Optional<PlantDTO> plantAttempt = plantService.findPlantDTOById(id);
        if(plantAttempt.isEmpty()){
            return new ResponseEntity<PlantDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PlantDTO>(plantAttempt.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Plant> store(@RequestBody Plant plant){
        return new ResponseEntity<Plant>(plantService.create(plant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> update(@RequestBody Plant plant, @PathVariable Integer id){
        if(plantService.findById(id).isEmpty()){
            return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Plant>(plantService.update(plant) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> delete(@PathVariable Integer id){
        if(plantService.findById(id).isEmpty()){
            return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
        }
        plantService.deleteById(id);
         return new ResponseEntity<Plant>(HttpStatus.NO_CONTENT);
    }
}
