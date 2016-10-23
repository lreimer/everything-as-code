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
package everything.`as`.code

import javax.enterprise.context.ApplicationScoped

/**
 * The Bookshelf manages our books.
 */
@ApplicationScoped
open class Bookshelf {

    private val books = listOf(
            Book("The Hitchhiker's Guide to the Galaxy", "0345391802", "Douglas Adams"),
            Book("The Martian", "0553418025", "Andy Weir"),
            Book("Guards! Guards!", "0062225758", "Terry Pratchett"),
            Book("Alice in Wonderland", "3458317422", "Lewis Carroll"),
            Book("Life, the Universe and Everything", "0345391829", "Douglas Adams"))

    /**
     * Get all books on the bookshelf.
     *
     * @return the list of all books
     */
    open fun all(): Collection<Book> = books

    /**
     * Find the book with the specified ISBN.
     *
     * @param isbn the ISBN-10 we are looking for
     * @return the book or NULL if not found
     */
    open fun byIsbn(isbn: String): Book? = books.find { it.isbn == isbn }

    /**
     * Get all books with the given title.
     *
     * @param title the books' title
     * @return the list of matching books
     */
    open fun byTitle(title: String?): Collection<Book> = books.filter { it.title == title }

}