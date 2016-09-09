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

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

/**
 * The book REST resource with basic operations.
 */
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
open class BookResource @Inject constructor(private val bookshelf: Bookshelf) {

    @GET
    open fun books(@QueryParam("title") title: String?): Collection<Book> {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1250))

        return if (title.isNullOrEmpty()) bookshelf.all() else bookshelf.byTitle(title)
    }

    @GET
    @Path("/{isbn}")
    open fun byIsbn(@PathParam("isbn") isbn: String): Response {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1250))

        val book = bookshelf.byIsbn(isbn)
        return if (book != null) Response.ok(book).build() else Response.status(Status.NOT_FOUND).build()
    }

}