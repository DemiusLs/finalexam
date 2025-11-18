package plants.finalexam.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plants.finalexam.Repository.BenefitRepository;
import plants.finalexam.Repository.ControindicationRepository;
import plants.finalexam.Repository.PlantRepository;
import plants.finalexam.dto.PlantDTO;
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
        delete(plant);
    }

    public List<Plant> searchAndSort(String search , String sortDir){
        boolean hasSearch = search != null && !search.isBlank();

        if(hasSearch){
            if(sortDir.equalsIgnoreCase("desc")){
                return plantRepo.findByCommonNameContainingIgnoreCaseOrScientificNameContainingIgnoreCaseOrderByCommonNameDesc(search, search);
            }else{
                return plantRepo.findByCommonNameContainingIgnoreCaseOrScientificNameContainingIgnoreCaseOrderByCommonNameAsc(search, search);
            }
        }else{
            if(sortDir.equalsIgnoreCase("desc")){
                return plantRepo.findAllByOrderByCommonNameDesc();
            }else{
                return plantRepo.findAllByOrderByCommonNameAsc();
            }
               
        }

    }


    //DTO
    @Transactional(readOnly = true)
    public Optional<PlantDTO> findPlantDTOById(Integer id) {
        return plantRepo.findById(id)
            .map(plant -> new PlantDTO(plant));
    }

    public List<PlantDTO> findAllPlantDTO(){
        List <Plant> plants = findAll();
       return plants.stream().map(plant -> new PlantDTO(plant)).collect(Collectors.toList());
    }

    public List<PlantDTO> searchAndSortDTO(String search, String sortDir) {
    
    List<Plant> plants = searchAndSort(search, sortDir);
        return plants.stream()
            .map(plant -> new PlantDTO(plant)) 
            .collect(Collectors.toList());
    }

}
