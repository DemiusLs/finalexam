package plants.finalexam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plants.finalexam.Repository.ControindicationRepository;
import plants.finalexam.Repository.PlantRepository;
import plants.finalexam.dto.BenefitDTO;
import plants.finalexam.dto.ControindicationDTO;
import plants.finalexam.model.Controindication;
import plants.finalexam.model.Plant;

@Service
public class ControindicationService {

    @Autowired
    private ControindicationRepository controindicationRepo;
    @Autowired
    private PlantRepository plantRepo;
    
    public List<Controindication> findAll(){
        return controindicationRepo.findAll();
    }

    public Controindication getById(Integer id){
        return controindicationRepo.findById(id).get();
    }

    public Optional<Controindication> findById(Integer id){
        return controindicationRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ControindicationDTO> findControindicationDTOById(Integer id) {
    return controindicationRepo.findById(id)
        .map(controindication -> new ControindicationDTO(controindication));
    }

    public Controindication create(Controindication controindication){
        return controindicationRepo.save(controindication);
    }

    public Controindication update(Controindication controindication){
        return controindicationRepo.save(controindication);
    }

    public void delete(Controindication controindication){
        for(Plant plant : controindication.getPlants()){
            plant.getControindications().remove(controindication);
            plantRepo.save(plant); 
        }
        controindicationRepo.delete(controindication);
    }

    public void deleteById(Integer id){
        Controindication controindication = getById(id);
        delete(controindication);
    }
}
