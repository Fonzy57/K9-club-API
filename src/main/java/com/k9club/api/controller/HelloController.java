// TODO SUPPRIMER CE FICHIER QUAND LES TESTS SERONT FINIS
package com.k9club.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String hello() {
    return "Hello World";
  }
}
