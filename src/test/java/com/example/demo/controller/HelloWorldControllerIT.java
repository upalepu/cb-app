/*
 * The MIT License
 *
 * Copyright 2018 Brian Mericle.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Greeting;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests the HelloWorldContorller. This was taken from here:
 * https://spring.io/guides/gs/testing-web/
 *
 * @author Brian Mericle
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerIT {

   private static final String URI = "/greet";
   private static final String URI_WITH_PARAM = "/greet?name=%s";

   @Autowired
   private TestRestTemplate restTemplate;

   @Test
   public void shouldReturnDefaultMessage() throws Exception {
      ResponseEntity<Greeting> responseEntity
              = restTemplate.getForEntity(URI, Greeting.class);
      assertThat(responseEntity).isNotNull();
      assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

      Greeting greeting = responseEntity.getBody();
      assertThat(greeting).isNotNull();

      String content = greeting.getContent();
      assertThat(content).isNotNull();
      assertThat(content).isEqualTo("Hello, Friend!");
   }

   @Test
   public void shouldReturnKnownMessage() throws Exception {
      ResponseEntity<Greeting> responseEntity
              = restTemplate.getForEntity(String.format(URI_WITH_PARAM, "Brian"), Greeting.class);
      assertThat(responseEntity).isNotNull();
      assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

      Greeting greeting = responseEntity.getBody();
      assertThat(greeting).isNotNull();

      String content = greeting.getContent();
      assertThat(content).isNotNull();
      assertThat(content).isEqualTo("Hello, Brian!");
   }

}
