package au.com.dogs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.dogs.persistence.model.Dog;
import au.com.dogs.service.DogService;
import au.com.dogs.utils.FormatUtils;
import au.com.dogs.utils.Message;

@RestController
@RequestMapping(value = "/api")
public class DogController {

	@Autowired
	DogService dogService;

	@RequestMapping(value = "/insert", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> insert() {
		Message msg = getRandomDogBreed();
		Dog insertedDog = createDogBreed(msg);
		if (insertedDog == null) {
			return new ResponseEntity<Object>("Something went wrong creating a new dog breed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Dog>(insertedDog, HttpStatus.OK);
	}

	@RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getDogBreedById(@PathVariable("id") Long id) {
		Dog dog = dogService.findById(id);
		if (dog == null) {
			return new ResponseEntity<Object>("Dog with id " + id + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dog>(dog, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

		Dog dog = dogService.findById(id);
		if (dog == null) {
			ResponseEntity<?> responseEntity = new ResponseEntity<Object>(
					"Unable to delete. Dog Breed with id " + id + " not found.", HttpStatus.NOT_FOUND);
			return responseEntity;
		}
		dogService.delete(id);
		return new ResponseEntity<Dog>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/breedname/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Dog>> findByBreedName(@PathVariable("name") String breedName) {
		List<Dog> response = dogService.findByBreedName(breedName);
		if (response.isEmpty() || response == null) {
			return new ResponseEntity<List<Dog>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Dog>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/breedname", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<String>> findByBreedName() {
		List<String> response = dogService.findBreedNames();
		if (response.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
	}

	private Message getRandomDogBreed() {
		RestTemplate restTemplate = new RestTemplate();
		Message message = restTemplate.getForObject("https://dog.ceo/api/breeds/image/random", Message.class);
		return message;
	}

	private Dog createDogBreed(Message msg) {
		String breed = FormatUtils.getBreenName(msg.getMessage());
		Dog dog = new Dog(breed, msg.getMessage(), LocalDate.now());
		dogService.save(dog);
		return dog;
	}

}
