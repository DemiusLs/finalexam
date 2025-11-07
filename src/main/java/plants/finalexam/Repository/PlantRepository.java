package plants.finalexam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.finalexam.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer>{

    
} 
