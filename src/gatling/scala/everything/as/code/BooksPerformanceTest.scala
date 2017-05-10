/**
  * MIT License
  *
  * Copyright (c) 2016 M.-Leander Reimer
  *
  * Permission is hereby granted, free of charge, to any person obtaining a copy
  * of this software and associated documentation files (the "Software"), to deal
  * in the Software without restriction, including without limitation the rights
  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  * copies of the Software, and to permit persons to whom the Software is
  * furnished to do so, subject to the following conditions:
  *
  * The above copyright notice and this permission notice shall be included in all
  * copies or substantial portions of the Software.
  *
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  * SOFTWARE.
  */
package everything.as.code

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * The Gatling load test simulation for our Books API.
  */
class BooksPerformanceTest extends Simulation {
  val conf = http
    .baseURL("http://localhost:18080")
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")

  // many more feeders available here
  val feeder = csv("books.csv").random

  val scn = scenario("Book Search")
    .exec(http("Get all books").get("/api/books"))
    .during(15 seconds) {
      feed(feeder)
        .exec(http("Get book by title ${Title}").get("/api/books?title=${Title}"))
        .pause(1 second)
        .exec(http("Get book with ISBN ${ISBN}").get("/api/books/${ISBN}"))
    }

  setUp(scn.inject(atOnceUsers(10), rampUsers(50) over (30 seconds)))
    .assertions(global.responseTime.max.lessThan(5000))
    .protocols(conf)
}