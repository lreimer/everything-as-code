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

import com.structurizr.Workspace
import com.structurizr.api.StructurizrClient
import com.structurizr.model.Tags
import com.structurizr.view.PaperSize
import com.structurizr.view.Shape
import com.structurizr.view.Styles
import spock.lang.Specification


/**
 * Describe the Architecture-as-code using Structurizr.
 */
class BooksArchitectureSpec extends Specification {

    def "Define the architecture using Structurizr"() {
        expect:
        def workspace = new Workspace("Everything-as-code", "This is a model of the system context of Everything-as-code.")
        def model = workspace.model

        // create a model and the software system we want to describe
        def bookService = model.addSoftwareSystem("Book Service", "The best source to get info on books.")

        // create the various types of people (roles) that use the software system
        def anonymousUser = model.addPerson("Anonymous User", "Anybody on the web.")
        anonymousUser.uses(bookService, "Searches for books and views details.")

        // now create the system context view based upon the model
        def viewSet = workspace.views
        def contextView = viewSet.createSystemContextView(bookService, "context", "The system context diagram for the book service.")
        contextView.addAllSoftwareSystems()
        contextView.addAllPeople()

        // tag and style some elements
        bookService.addTags("Everything-as-code")

        Styles styles = viewSet.configuration.styles
        styles.addElementStyle(Tags.ELEMENT).width(600).height(450).color("#FFFFFF").fontSize(40).shape(Shape.RoundedBox)
        styles.addElementStyle("Everything-as-code").background("#041F37")
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#2A4E6E")
        styles.addElementStyle(Tags.PERSON).width(575).background("#728DA5").shape(Shape.Person)
        styles.addRelationshipStyle(Tags.RELATIONSHIP).thickness(5).fontSize(40).width(500)
        contextView.setPaperSize(PaperSize.Slide_4_3)

        def client = new StructurizrClient("4c41190b-fe56-40d3-97b0-c06687c1ca10", "6d28eecd-13ad-4292-ab31-c9ea762fb7d2")
        client.putWorkspace(22571, workspace)
    }

}