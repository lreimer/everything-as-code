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

import spock.lang.Specification

import java.util.logging.Logger

/**
 * An interaction spec for our book REST resource.
 */
class BookResourceSpec extends Specification {

    Bookshelf bookshelf
    BookResource resource

    void setup() {
        bookshelf = Mock(Bookshelf)
        resource = new BookResource(bookshelf, Stub(Logger))
    }

    def "Get list of all books"() {
        given: 'a fresh resource and initialized mock'
        bookshelf.all() >> [new Book('Title', 'ISBN', 'Unknown')]

        when: 'we GET the list of all books'
        def books = resource.books(null)

        then: 'the test book should be returned'
        books.size() == 1
        books[0].title == 'Title'
        books[0].isbn == 'ISBN'
        books[0].author == 'Unknown'
    }

    def "Get a book by its title"() {
        given:
        bookshelf.byTitle("Title") >> [new Book('Title', '4711', 'Unknown')]

        expect:
        resource.books("Title").size() == 1
    }

    def "Get a book by its ISBN"() {
        given:
        def book = new Book('Title', '4711', 'Unknown')

        when:
        def response = resource.byIsbn("4711")

        then:
        1 * bookshelf.byIsbn("4711") >> book
        response.status == 200
        response.entity == book
    }
}
