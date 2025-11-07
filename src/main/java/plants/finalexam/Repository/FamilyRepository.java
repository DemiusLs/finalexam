package plants.finalexam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.finalexam.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Integer>{

    
}