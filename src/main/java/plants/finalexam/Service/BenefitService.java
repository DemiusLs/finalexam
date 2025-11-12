package plants.finalexam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plants.finalexam.Repository.BenefitRepository;
import plants.finalexam.Repository.PlantRepository;
import plants.finalexam.dto.BenefitDTO;
import plants.finalexam.dto.PlantDTO;
import plants.finalexam.model.Benefit;
import plants.finalexam.model.Plant;

@Service
public class BenefitService {
    
    @Autowired
    private BenefitRepository benefitRepo;
    @Autowired
    private PlantRepository plantRepo;


    public List<Benefit> findAll(){
        return benefitRepo.findAll();
    }

    public Benefit getById(Integer id){
        return benefitRepo.findById(id).get();
    }

    public Optional<Benefit> findById(Integer id){
        return benefitRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<BenefitDTO> findBenefitDTOById(Integer id) {
    return benefitRepo.findById(id)
        .map(benefit -> new BenefitDTO(benefit));
    }

    public Benefit create(Benefit benefit){
        return benefitRepo.save(benefit);
    }

    public Benefit update(Benefit benefit){
        return benefitRepo.save(benefit);
    }

    public void delete(Benefit benefit){
        for(Plant plant : benefit.getPlants()){
            plant.getBenefits().remove(benefit);
            plantRepo.save(plant);
        }
        benefitRepo.delete(benefit);
    }

    public void deleteById(Integer id){
        Benefit benefit = getById(id);
        delete(benefit);
    }

}
