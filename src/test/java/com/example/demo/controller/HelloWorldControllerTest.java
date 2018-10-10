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
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests the HelloWorldContorller. This was taken from here:
 * https://spring.io/guides/gs/testing-web/
 *
 * @author Brian Mericle
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

   private static final String URI = "/greet";
   private static final String URI_WITH_PARAM = "/greet?name=%s";

   @Autowired
   private HelloWorldController controller;

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void contexLoads() throws Exception {
      assertThat(controller).isNotNull();
   }

   @Test
   public void shouldReturnDefaultMessage() throws Exception {
      this.mockMvc.perform(
              get(URI))
              .andExpect(status().isOk())
              .andExpect(content().string(containsString("Hello, Stranger!")));
   }

   @Test
   public void shouldReturnKnownMessage() throws Exception {
      this.mockMvc.perform(
              get(String.format(URI_WITH_PARAM, "Brian")))
              .andExpect(status().isOk())
              .andExpect(content().string(containsString("Hello, Brian!")));
   }

}
