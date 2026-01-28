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

import plants.finalexam.Service.PlantService;
import plants.finalexam.dto.PlantDTO;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/plants")
public class PlantRestController {
    
    @Autowired
    private PlantService plantService;
    
    @GetMapping
    public ResponseEntity<List<PlantDTO>> index(){
        return new ResponseEntity<>(plantService.findAllPlantsDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantDTO> show(@PathVariable Integer id){
        Optional<PlantDTO> plantAttempt = plantService.findPlantDTOById(id);
        if(plantAttempt.isEmpty()){
            return new ResponseEntity<PlantDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PlantDTO>(plantAttempt.get(), HttpStatus.OK);
    }

    
}
