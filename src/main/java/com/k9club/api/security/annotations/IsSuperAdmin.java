package com.k9club.api.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to secure methods or classes,
 * allowing access only to users having the SUPER_ADMIN role.
 */
// Specifies where the annotation can be used: on methods and on classes.
@Target({ElementType.METHOD, ElementType.TYPE})
// Specifies that the annotation is available at runtime for reflection (Spring Security needs it during execution).
@Retention(RetentionPolicy.RUNTIME)
// Restricts access: only users with the SUPER_ADMIN role can access the annotated method or class.
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
public @interface IsSuperAdmin {
}
