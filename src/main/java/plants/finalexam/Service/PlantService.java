package plants.finalexam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plants.finalexam.Repository.BenefitRepository;
import plants.finalexam.Repository.ControindicationRepository;
import plants.finalexam.Repository.PlantRepository;
import plants.finalexam.model.Benefit;
import plants.finalexam.model.Controindication;
import plants.finalexam.model.Plant;

@Service
public class PlantService {
    
    @Autowired
    private PlantRepository plantRepo;
    @Autowired
    private BenefitRepository benefitRepo;
    @Autowired
    private ControindicationRepository controindicationRepo;

    public List<Plant> findAll(){
       return plantRepo.findAll();
    }

    public Plant getById(Integer id){
        return plantRepo.findById(id).get();
    }

    public Optional<Plant>findById(Integer id){
        return plantRepo.findById(id);
    }

    public Plant create(Plant plant){
        return plantRepo.save(plant);
    }

    public Plant update(Plant plant){
        return plantRepo.save(plant);
    }

    public void delete(Plant plant){

        for(Benefit benefit : plant.getBenefits()){
            benefit.getPlants().remove(plant);
            benefitRepo.save(benefit);
        }

        for(Controindication controindication : plant.getControindications()){
            controindication.getPlants().remove(plant);
            controindicationRepo.save(controindication);
        }

        plantRepo.delete(plant);
    }

    public void deleteById(Integer id){
        Plant plant = getById(id);
        plantRepo.delete(plant);
    }
}
