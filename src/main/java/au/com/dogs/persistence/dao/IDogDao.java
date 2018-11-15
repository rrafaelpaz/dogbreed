package au.com.dogs.persistence.dao;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.dogs.persistence.model.Dog;
public interface IDogDao extends JpaRepository<Dog, Long> {

    @Query("SELECT d FROM Dog d WHERE LOWER(d.breedName) = LOWER(:breedName)")
    List<Dog> searchByBreedName(@Param("breedName") String breedName);
    
    @Query("SELECT DISTINCT(d.breedName) FROM Dog d")
    List<String> findBreedNames();
}
