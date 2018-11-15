package au.com.dogs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.dogs.persistence.dao.IDogDao;
import au.com.dogs.persistence.model.Dog;

@Service("dogService")
public class DogService {

	@Autowired
	IDogDao dogDao;
	
	@Transactional
	public void save(Dog dog){
		 dogDao.save(dog);
	}
	
	@Transactional
	public List<Dog> getAllDogs(){
		return dogDao.findAll();
	}
	
	@Transactional
	public Dog findById(Long id){
		return dogDao.findOne(id);
	}
	
	@Transactional
	public void delete(Long id){
		dogDao.delete(id);
	}
	
	@Transactional
	public List<Dog> findByBreedName(String breedName){
		return dogDao.searchByBreedName(breedName);
	}
	
	@Transactional
	public List<String> findBreedNames(){
		return dogDao.findBreedNames();
	}
}
