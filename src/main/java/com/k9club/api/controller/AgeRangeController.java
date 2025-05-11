package com.k9club.api.controller;

import com.k9club.api.dao.AgeRangeDao;
import com.k9club.api.model.AgeRange;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsCoach;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing age ranges.
 * <p>
 * All endpoints require ADMIN privileges by default (@IsAdmin at class level),
 * except for retrieving a single age range, which can also be accessed by the owner (@IsOwner).
 */
@RestController
@CrossOrigin
@IsAdmin
public class AgeRangeController {

  // TODO FAIRE LES JSON VIEW

  private final AgeRangeDao ageRangeDao;

  /**
   * Constructs a new AgeRangeController with the given DAO.
   *
   * @param ageRangeDao the DAO used to perform CRUD operations on AgeRange entities
   */
  @Autowired
  public AgeRangeController(AgeRangeDao ageRangeDao) {
    this.ageRangeDao = ageRangeDao;
  }

  /**
   * Retrieves all defined age ranges.
   * <p>
   * Accessible only to COACH users.
   *
   * @return a ResponseEntity containing the list of AgeRange entities and HTTP 200 OK
   */
  @IsCoach
  @GetMapping("/age-ranges")
  // @JsonView(ViewUser.Owner.class)
  public ResponseEntity<List<AgeRange>> getAllAgeRanges() {
    List<AgeRange> list = ageRangeDao.findAll();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * Retrieves a specific age range by its ID.
   * <p>
   * Accessible to OWNER users (@IsOwner) or COACH, or ADMIN users.
   * Returns HTTP 404 if no age range exists with the given ID.
   *
   * @param id the ID of the AgeRange to retrieve
   * @return a ResponseEntity containing the AgeRange and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @IsOwner
  @GetMapping("/age-range/{id}")
  public ResponseEntity<AgeRange> getAgeRangeById(@PathVariable Long id) {
    Optional<AgeRange> optionalAgeRange = ageRangeDao.findById(id);
    if (optionalAgeRange.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalAgeRange.get(), HttpStatus.OK);
  }


  /**
   * Creates a new age range.
   * <p>
   * Accessible only to ADMIN users.
   *
   * @param ageRange the AgeRange object to create
   * @return a ResponseEntity containing the created AgeRange and HTTP 200 OK
   */
  @PostMapping("/age-range")
  public ResponseEntity<AgeRange> addAgeRange(@RequestBody @Valid AgeRange ageRange) {
    ageRange.setId(null);
    ageRangeDao.save(ageRange);
    return new ResponseEntity<>(ageRange, HttpStatus.OK);
  }

  /**
   * Updates an existing age range by ID.
   * <p>
   * Accessible only to ADMIN users.
   * Returns HTTP 404 if no age range exists with the given ID.
   *
   * @param id       the ID of the AgeRange to update
   * @param ageRange the AgeRange object containing updated minAge and maxAge
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the AgeRange does not exist
   */
  @PutMapping("/age-range/{id}")
  public ResponseEntity<Void> updateAgeRange(@PathVariable Long id, @RequestBody @Valid AgeRange ageRange) {
    Optional<AgeRange> optionalAgeRange = ageRangeDao.findById(id);
    if (optionalAgeRange.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AgeRange existingAgeRange = optionalAgeRange.get();

    existingAgeRange.setId(id);
    existingAgeRange.setMinAge(ageRange.getMinAge());
    existingAgeRange.setMaxAge(ageRange.getMaxAge());

    ageRangeDao.save(existingAgeRange);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes an age range by ID.
   * <p>
   * Accessible only to ADMIN users.
   * Returns HTTP 404 if no age range exists with the given ID.
   * <p>
   * Consider anonymizing related data instead of performing a hard delete.
   *
   * @param id the ID of the AgeRange to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the AgeRange does not exist
   */
  @DeleteMapping("/age-range/{id}")
  public ResponseEntity<Void> deleteAgeRange(@PathVariable Long id) {
    Optional<AgeRange> optionalAgeRange = ageRangeDao.findById(id);

    if (optionalAgeRange.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    ageRangeDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
