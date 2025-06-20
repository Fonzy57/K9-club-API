package com.k9club.api.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Configuration class for customizing the Jackson ObjectMapper.
 * <p>
 * Registers a global deserializer that strips leading and trailing Unicode
 * whitespace from all JSON string values during deserialization.
 */
@Configuration
public class JacksonConfig {

  /**
   * Defines a Jackson2ObjectMapperBuilderCustomizer bean that applies
   * a custom String deserializer to strip whitespace from incoming JSON strings.
   *
   * @return a Jackson2ObjectMapperBuilderCustomizer which registers the strip-deserializer
   * for the String type
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer trimStrings() {
    // Create a custom JsonDeserializer that trims whitespace from String values
    JsonDeserializer<String> trimDeserializer = new JsonDeserializer<>() {

      /**
       * Reads the JSON string value and returns it stripped of leading/trailing Unicode whitespace.
       *
       * @param parser the JsonParser used to read the JSON content
       * @param context the DeserializationContext for additional configuration
       * @return the stripped String, or null if the original value was null
       * @throws IOException if an error occurs while reading from the parser
       */
      @Override
      public String deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String val = parser.getValueAsString();
        return (val != null ? val.strip() : null);
      }
    };

    // Register the custom deserializer for all String properties
    return builder -> builder.deserializerByType(String.class, trimDeserializer);
  }
}
