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
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Basic Spock specification for our bookshelf.
 */
class BookshelfSpec extends Specification {

    @Subject
    def bookshelf = new Bookshelf()

    def "Check all books are there"() {
        expect: 'the correct number of books'
        bookshelf.all().size() == 5
    }

    @Unroll
    def "Find book #title by ISBN #isbn"() {
        when: 'we search a book by ISBN'
        def book = bookshelf.byIsbn(isbn)

        then: 'the title and author are correct'
        book?.title == title
        book?.author == author

        where:
        isbn         || title | author
        "0345391802" || "The Hitchhiker's Guide to the Galaxy" | "Douglas Adams"
        "0345391829" || "Life, the Universe and Everything" | "Douglas Adams"
    }

    def "Search a known book by it's title"() {
        expect: 'we search a book by a known title'
        bookshelf.byTitle("Life, the Universe and Everything").size() == 1
    }

    def "Search an unknown book by it's title"() {
        expect: 'we search a book by an unknown title'
        bookshelf.byTitle("Everything-as-code").size() == 0
    }
}
