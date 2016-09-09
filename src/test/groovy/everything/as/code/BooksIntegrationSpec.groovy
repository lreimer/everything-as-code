/*
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

import io.restassured.specification.RequestSpecification
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.*
import static org.hamcrest.Matchers.equalTo

/**
 * An integration test using RestAssured to check proper REST API behaviour
 * over the wire.
 */
class BooksIntegrationSpec extends Specification {

    @Shared
    RequestSpecification request

    void setupSpec() {
        request = given().config(config()).baseUri('http://localhost').port(18080)
    }

    def "Get all books is OK."() {
        when:
        def response = request.get('/api/books')

        then:
        with(response) {
            statusCode == 200
            expect().body("length()", equalTo(2))
        }
    }

    @Unroll
    def "Get book by title is HTTP #status"() {
        when:
        def response = request.param('title', title).get('/api/books')

        then:
        with(response) {
            statusCode == status
            expect().body("title", equalTo(title))
            expect().body("isbn", equalTo(isbn))
        }

        where:
        title                                  || status | isbn
        "The Hitchhiker's Guide to the Galaxy" || 200 | "0345391802"
        "Life, the Universe and Everything"    || 200 | "0345391829"
    }

    @Unroll
    def "Get book by ISBN #isbn is HTTP #status"() {
        when:
        def response = request.get("/api/books/$isbn")

        then:
        with(response) {
            statusCode == status
            expect().body("isbn", equalTo(isbn))
        }

        where:
        isbn         || status
        "0345391802" || 200
        "0345391829" || 200
        "1234567890" || 404
    }
}