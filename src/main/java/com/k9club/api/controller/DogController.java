package com.k9club.api.controller;

import com.k9club.api.dao.DogDao;
import com.k9club.api.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class DogController {

  @Autowired
  protected DogDao dogDao;

  @GetMapping("/dogs")
  public ResponseEntity<List<Dog>> dogsList() {
    return new ResponseEntity<>(dogDao.findAll(), HttpStatus.OK);
  }

  @GetMapping("/dog/{id}")
  public ResponseEntity<Dog> dog(@PathVariable int id) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalDog.get(), HttpStatus.OK);
  }

  @DeleteMapping("/dog/{id}")
  public ResponseEntity<Dog> deleteDog(@PathVariable int id) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    dogDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/dog")
  public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
    dog.setId(null);

    dogDao.save(dog);

    return new ResponseEntity<>(dog, HttpStatus.CREATED);
  }

  @PutMapping("/dog/{id}")
  public ResponseEntity<Dog> updateDog(@PathVariable int id, @RequestBody Dog dog) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    dog.setId(id);

    // User cannot change creation date
    dog.setCreatedAt(optionalDog.get().getCreatedAt());

    dogDao.save(dog);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
