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

import com.example.demo.model.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world endpoint. This was taken from
 * <a href="https://spring.io/guides/gs/actuator-service/">here</a>.
 *
 * @author Brian Mericle
 */
@Controller
public final class HelloWorldController {

   /**
    * Constant for the content.
    */
   private static final String TEMPLATE = "Hello, %s!";

   /**
    * Atomic counter to enable creating a new id.
    */
   private final AtomicLong counter = new AtomicLong();

   /**
    * Returns a nice hello greeting message.
    *
    * @param name the name to use in the greeting.
    * @return a new greeting.
    */
   @GetMapping("/greet")
   @ResponseBody
   public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Friend") final String name) {
      return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
   }
}
