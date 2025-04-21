package com.market.product;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApplicationQueueConfig {

  @Bean
  public Jackson2JsonMessageConverter productJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
