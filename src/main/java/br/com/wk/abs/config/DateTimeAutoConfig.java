package br.com.wk.abs.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateTimeAutoConfig {

  /*
  * https://medium.com/@andylke/rest-controller-configure-date-time-format-in-json-response-201e97aa74b0
  * */

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer
  jacksonObjectMapperBuilderCustomizer() {
    return new Jackson2ObjectMapperBuilderCustomizer() {

      @Override
      public void customize(
          Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        final String dateFormat = "dd/MM/yyyy";
        final String timeFormat = "hh:mm:ss S";
        final String dateTimeFormat = "dd/MM/yyyy hh:mm:ss S";
        jacksonObjectMapperBuilder
            .serializers(
                new LocalDateSerializer(
                    DateTimeFormatter.ofPattern(dateFormat)))
            .deserializers(
                new LocalDateDeserializer(
                    DateTimeFormatter.ofPattern(dateFormat)))
            .serializers(
                new LocalTimeSerializer(DateTimeFormatter.ofPattern
                    (timeFormat)))
            .deserializers(
                new LocalTimeDeserializer(
                    DateTimeFormatter.ofPattern(timeFormat)))
            .serializers(
                new LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern(dateTimeFormat)))
            .deserializers(
                new LocalDateTimeDeserializer(
                    DateTimeFormatter.ofPattern(dateTimeFormat)));
      }
    };
  }
}
