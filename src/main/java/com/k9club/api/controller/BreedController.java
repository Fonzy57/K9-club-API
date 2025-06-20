package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.BreedDao;
import com.k9club.api.dto.breed.BreedCreateDto;
import com.k9club.api.dto.breed.BreedUpdateDto;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.model.Breed;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsCoach;
import com.k9club.api.security.annotations.IsOwner;
import com.k9club.api.security.annotations.IsSuperAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing dog breeds.
 * <p>
 * Owners can list and view breeds; Admins can create and update breeds;
 * Super-admins can delete breeds.
 */
@RestController
@CrossOrigin
@IsOwner
public class BreedController {

  // TODO FAIRE LES JSON VIEWS

  private final BreedDao breedDao;

  /**
   * Constructs a new BreedController with the given DAO.
   *
   * @param breedDao the DAO used to perform CRUD operations on Breed entities
   */
  @Autowired
  public BreedController(BreedDao breedDao) {
    this.breedDao = breedDao;
  }

  @IsAdmin
  @JsonView(ViewsAdmin.Basic.class)
  @GetMapping("/admin/breeds")
  public ResponseEntity<List<Breed>> getAllBreedsForAdmin() {
    List<Breed> breeds = breedDao.findAll();
    return new ResponseEntity<>(breeds, HttpStatus.OK);
  }

  @IsAdmin
  @JsonView(ViewsAdmin.Basic.class)
  @GetMapping("/admin/breed/{id}")
  public ResponseEntity<Breed> getBreedByIdForAdmin(@PathVariable Long id) {
    Optional<Breed> optionalBreed = breedDao.findById(id);

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalBreed.get(), HttpStatus.OK);
  }

  @IsCoach
  @JsonView(ViewsCoach.Basic.class)
  @GetMapping("/coach/breeds")
  public ResponseEntity<List<Breed>> getAllBreedsForCoach() {
    List<Breed> breeds = breedDao.findAll();
    return new ResponseEntity<>(breeds, HttpStatus.OK);
  }

  @IsCoach
  @JsonView(ViewsCoach.Basic.class)
  @GetMapping("/coach/breed/{id}")
  public ResponseEntity<Breed> getBreedByIdForCoach(@PathVariable Long id) {
    Optional<Breed> optionalBreed = breedDao.findById(id);

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalBreed.get(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.Basic.class)
  @GetMapping("/breeds")
  public ResponseEntity<List<Breed>> getAllBreeds() {
    List<Breed> breeds = breedDao.findAll();
    return new ResponseEntity<>(breeds, HttpStatus.OK);
  }


  @JsonView(ViewsOwner.Basic.class)
  @GetMapping("/breed/{id}")
  public ResponseEntity<Breed> getBreedById(@PathVariable Long id) {
    Optional<Breed> optionalBreed = breedDao.findById(id);

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalBreed.get(), HttpStatus.OK);
  }

  /**
   * Creates a new breed.
   * Only accessible by Admin users.
   *
   * @param breedCreateDto the DTO containing name and validation for the new breed
   * @return a ResponseEntity containing the created Breed and HTTP 201 Created
   */
  @IsAdmin
  @PostMapping("/breed")
  public ResponseEntity<Breed> addBreed(@RequestBody @Valid BreedCreateDto breedCreateDto) {
    Breed breed = new Breed();
    breed.setName(breedCreateDto.getName());

    breedDao.save(breed);

    return new ResponseEntity<>(breed, HttpStatus.CREATED);
  }

  /**
   * Updates an existing breed by its ID.
   * Only accessible by Admin users.
   * Returns HTTP 404 if no breed exists with the given ID.
   *
   * @param id             the ID of the breed to update
   * @param breedUpdateDto the DTO containing updated name for the breed
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the breed does not exist
   */
  @IsAdmin
  @PutMapping("/breed/{id}")
  public ResponseEntity<Void> updateBreed(@PathVariable Long id, @RequestBody @Valid BreedUpdateDto breedUpdateDto) {
    Optional<Breed> optionalBreed = breedDao.findById(id);

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Breed existingBreed = optionalBreed.get();

    existingBreed.setName(breedUpdateDto.getName());

    breedDao.save(existingBreed);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  /**
   * Deletes a breed by its ID.
   * Only accessible by Super-admin users.
   * Returns HTTP 404 if no breed exists with the given ID.
   * <p>
   * Consider anonymizing related data instead of performing a hard delete.
   *
   * @param id the ID of the breed to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the breed does not exist
   */
  @IsSuperAdmin
  @DeleteMapping("/breed/{id}")
  public ResponseEntity<Void> deleteBreed(@PathVariable Long id) {
    Optional<Breed> optionalBreed = breedDao.findById(id);

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    breedDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
