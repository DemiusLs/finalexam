package plants.finalexam.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.finalexam.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer>{

    List<Plant> findAllByOrderByCommonNameAsc();
    List<Plant> findAllByOrderByCommonNameDesc();

    List<Plant> findByCommonNameContainingIgnoreCaseOrScientificNameContainingIgnoreCaseOrderByCommonNameAsc(
            String commonName,
            String scientificName
    );

    List<Plant> findByCommonNameContainingIgnoreCaseOrScientificNameContainingIgnoreCaseOrderByCommonNameDesc(
            String commonName,
            String scientificName
    );
} 
