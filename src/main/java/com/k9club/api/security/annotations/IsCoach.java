package com.k9club.api.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to secure methods or classes,
 * allowing access only to users having either the COACH or ADMIN role.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('ROLE_COACH', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
public @interface IsCoach {
}
