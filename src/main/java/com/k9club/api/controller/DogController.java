package com.k9club.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DogController {

  @GetMapping("/dogs")
  public String dogsList() {
    return "Dogs List";
  }

  @GetMapping("/dog/{id}")
  public String dog(@PathVariable int id) {
    return "Dog with ID : " + id;
  }

  @DeleteMapping("/dog/{id}")
  public String deleteDog(@PathVariable int id) {
    return "DELETE Dog with ID : " + id;
  }

  @PostMapping("/dog")
  public String addDog() {
    return "Add Dog";
  }

  @PutMapping("/dog/{id}")
  public String modifyDog(@PathVariable int id) {
    return "Modify Dog with ID : " + id;
  }


}
