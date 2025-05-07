package com.k9club.api.security.interfaces;

import com.k9club.api.security.AppUserDetails;

public interface ISecurityUtils {

  String getRole(AppUserDetails userDetails);

  String generateToken(AppUserDetails userDetails);

  String getSubjectFromJwt(String jwt);

}
