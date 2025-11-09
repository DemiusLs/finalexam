
package plants.finalexam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plants.finalexam.Repository.FamilyRepository;
import plants.finalexam.Repository.PlantRepository;
import plants.finalexam.model.Family;
import plants.finalexam.model.Plant;


@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepo;
    @Autowired
    private PlantRepository plantRepo;

    public List<Family> findAll(){
        return familyRepo.findAll();
    }

    public Family getById(Integer id){
        return familyRepo.findById(id).get();
    }

    public Optional<Family> findById(Integer id){
        return familyRepo.findById(id);
    }

    public Family create(Family family){
        return familyRepo.save(family);
    }

    public Family update(Family family){
        return familyRepo.save(family);
    }

    public void delete(Family family){
        for(Plant plant : family.getPlants()){
            plant.setFamily(null);
            plantRepo.save(plant);            
        }
        familyRepo.delete(family);
    }

    public void deleteById(Integer id){
        Family family = getById(id);
        delete(family);
    }
    
} 