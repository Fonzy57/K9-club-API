package com.k9club.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CoachRegistrationDto extends UserBasicUpdateDto {

  //  @Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,\\-./:;<=>?@[\\\\\\]^_`{|}~]).{10,40}$/",
//      message = "Le mot de masse doit contenir entre 8 et 40 caractères. Il doit contenir au moins une majuscule, une" +
//          " minuscule, un chiffre et un caractère spécial.")
  @NotBlank(message = "Le mot de passe est obligatoire")
  @Length(min = 8, max = 40)
  protected String password;
}
