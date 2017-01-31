// _OOP 2017_ // Everything-as-code -> { created with :heart: and :coffee: by @LeanderReimer @qaware }

# _Everything-as-code._

## Polyglotte Entwicklung in der Praxis.

---

![left 175%](Images/myself.png)

## #whoami

**Mario-Leander Reimer**
Cheftechnologe, QAware GmbH

- Vollblut Entwickler && Architekt
- #CloudNativeNerd
- Open Source Enthusiast

mario-leander.reimer@qaware.de
http://github.com/lreimer
http://speakerdeck.com/lreimer

---
^ Coffee kitchen talk: crazy developers chatting and ranting and ...
They are all wrong. Real programmers use IntelliJ.

![inline](http://imgs.xkcd.com/comics/real_programmers.png)

---

# Welche Sprache verwenden _echte_ Programmierer?

---
^ Most of them not so relevant anymore.

## Meine #FirstSevenLanguages

- Pascal
- Basic
- C / C++
- Assembler
- PHP
- Java
- C#

---
^ This is my personal choice!
Randomly combining these surely is not such a great idea.
Which one is your favorite language?

## Meine #LastSevenLanguages

- Java
- Groovy
- TypeScript
- Ruby
- Kotlin
- Scala
- Go

---
^ Java vs. C.

![inline](https://adtmag.com/articles/2015/01/08/~/media/ECG/adtmag/Images/2014/01/tiobe_2014.png)

---
^ Here it is Python.

![inline](http://static1.squarespace.com/static/51361f2fe4b0f24e710af7ae/54b5c35ee4b0b6572f6dac96/54b5c367e4b0226a8ffadefe/1421198184280/codeeval2015.001.jpg?format=1500w)

---

![inline](http://sogrady-media.redmonk.com/sogrady/files/2015/01/lang.rank_.plot_.q1152.png)

---

## Es gibt keine _einstimmige_ Meinung ...

- http://spectrum.ieee.org/computing/software/the-2015-top-ten-programming-languages
- http://spectrum.ieee.org/computing/software/the-2016-top-programming-languages
- https://www.sitepoint.com/whats-best-programming-language-learn-2015/

---

# _Die beste Programmiersprache_ gibt es nicht!
## Auf den _Kontext_ kommt es an.

---

![](Images/workbench.png)
# Die _IDE_ ist unsere _Werkbank_.

---
^ The idea of a conveyor belt.

## Unsere Definition von Software Industrialisierung

- _Hat nichts mit billiger Arbeitskraft zu tun!_
- Hoher Automatisiersgrad von arbeitsintensiven und wiederkehrenden Arbeitsschritten
- Höhere Software-Qualität durch abgestimmte Tool-Chain
- Mehr Produktivität und Zufriedenheit der Teams
- Bessere Kosten-Effizienz und Wettbewerbsfähigkeit

---
^ Everything is made from code. Everything is made by code.

# Wäre es nicht cool wenn ...

```kotlin


open fun everythingAsCode() : Boolean {
   everytingIsMadeFromCode() && everythingIsMadeByCode()
}

val softwareIndustrialization = everythingAsCode()
```

---

## The Quest for an ideal Polyglot Project Archetype
^ Learn from our projects. Tailor made and streamlined project setup.

- Welche Sprachen werden in unseren Projekten verwendet?
- Welche Tools verwenden wir für Setup, Build, Code, Test, CI, Infrastructure und Dokumentation?
- Was davon hat sich bewährt und was eher nicht?
- Gibt es bereits Best Practices für den Einsatz in der Praxis?

__+ Wishful Greenfield Thinking!__

---

# _SEU_-as-code

---

## Lightweight Developer Provisioning mit Gradle

- _[ SEU ]_ -> Software Entwicklungs Umgebung
- Nutzung von Gradle als Build-Tool für das Setup und die Aktualisierung unserer Entwicklungsumgebungen
- Software-Pakete werden als Dependencies ausgedrückt
- Gradle Tasks and Groovy Skripte statt Shell-Scripting
- Versionskontrolle der SEU Definition und Skripte

---
^ Switch to demo here.

```groovy
plugins { id 'de.qaware.seu.as.code.base' version '2.4.0' }

import static de.qaware.seu.as.code.plugins.base.Platform.isMac

seuAsCode {
    seuHome = { if (isMac()) '/Volumes/Everything-as-code' else 'Y:' }
    projectName = 'Everything-as-code'
}

dependencies {
    // list of software dependencies ...
    software 'org.groovy-lang:groovy:2.4.7'
    software 'org.scala-lang:scala:2.11.8'
    software 'org.jruby:jruby:9.1.4.0'
}
```

---
^ Which build tool to use? Maven? Gradle? SBT? Ant? Gulp? Buildr?
Our projects and customers still use Maven quite a lot.

# _Build_-as-code

---

## Maven ist gut. Gradle ist besser.
^ Nothing against Maven. But it's time to move along.

- Sehr flexibel und vielseitig einsetzbar.
- Einfache Unterstützung für Polyglotte Projekte.
- Build Skripte sind maximal kurz und prägnant.
- Drastisch reduzierte Build-Zeiten durch Incremental Builds.
- Zahlreiche neue Features: Composite Builds, Kotlin-basierte Build-Skripte, Performance Verbesserungen, ...
- Regelmäßige Releases. Stabil und ausgereift.

---

```groovy
apply plugin: 'application'
apply plugin: 'war'
apply plugin: 'kotlin'
apply plugin: 'groovy'

repositories { jcenter() }

dependencies {
    providedCompile 'fish.payara.extras:payara-micro:4.1.1.163'
    // and many more ...
}

task everythingAsCode() << {
    println 'Everything-as-code @ OOP 2017.'
}

```

---

# _Main_-as-code

---

# _Java_ ist nach wie vor die primäre Implementierungssprache!
## _Und das ist gut so!_

---
^ Wishful thinking. Java is OK. Kotlin is better.
Good Java integration. Short syntax, easy to learn.
Statically typed. Compiled. Small footprint.

## Für die Mutigen: _Kotlin_ als ernsthafte Alternative zu Java.

---

## Warum Kotlin? Und nicht Scala, Clojure, ...

- Für Java Entwickler sehr schnell zu erlernen.
- Sehr ausgewogene Universalsprache.
- Null Safety + jede Menge andere nützliche Features.
- JDK6 kompatibel. Kleine Library-Größe.
- Sehr guter IDE Support.
- **Wishful Greenfield Thinking.**

---

```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
data class Book(val title: String, val isbn: String, val author: String) { }

@ApplicationScoped
open class Bookshelf {
    private val books = listOf(Book("The Hitchhiker's Guide to the Galaxy", "0345391802"))
    open fun byIsbn(isbn: String): Book? = books.find { it.isbn == isbn }
}

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
open class BookResource @Inject constructor(private val bookshelf: Bookshelf) {
    @GET @Path("/{isbn}")
    open fun byIsbn(@PathParam("isbn") isbn: String): Response {
        val book = bookshelf.byIsbn(isbn)
        return if (book != null) Response.ok(book).build() else Response.status(Status.NOT_FOUND).build()
    }
}

@ApplicationPath("api")
class BookstoreAPI : Application() {
    override fun getClasses() = hashSetOf(JacksonFeature::class.java, BookResource::class.java)
}
```
---
^ Not my cup of tea. Aber der Vollständigkeit halber.

# _Frontend_-as-code

---
^ Build tools gulp / grunt / broccoli / * optional.

## Willkommen in der JavaScript Wunderwelt.

- Ein Universum für sich!
- Klarer Trend: Single Page Webapplikationen.
- _HTML5 + CSS3 +_ __?__
  - __?__ = TypeScript oder
  - __?__ = ECMAScript2015 + Babel
- Rückgrat des Builds: _node + npm + webpack_

---
^ There are a lot of frameworks. JUnit, Hamcrest, Mockito, Cucumber, ...
Some good, some bad. Some verbose.

# _Test_-as-code

---
^ Spock is super expressive. We can focus on the test and not the language.
Groovy helps a lot here.

## Groovy und Spock für Unit & Integration Tests

```groovy
class BookshelfSpec extends Specification {
    @Subject
    def bookshelf = new Bookshelf()

    @Unroll
    def "Find book #title by ISBN #isbn"() {
        when: 'we search a book by ISBN'
        def book = bookshelf.byIsbn(isbn)

        then: 'the title and author are correct'
        book?.title == title
        book?.author == author

        where:
        isbn         || title                                  | author
        "0345391802" || "The Hitchhiker's Guide to the Galaxy" | "Douglas Adams"
        "0345391829" || "Life, the Universe and Everything"    | "Douglas Adams"
    }
}
```

---

## Scala und Gatling für Last-Tests

```scala
class BooksPerformanceTest extends Simulation {
  val conf = http.baseURL("http://localhost:18080").acceptHeader("application/json")

  val feeder = csv("books.csv").random

  val scn = scenario("Book Search")
    .exec(http("Get all books").get("/api/books"))
    .during(30 seconds) {
      feed(feeder)
        .exec(http("Get book by title ${Title}").get("/api/books?title=${Title}"))
        .pause(1 second)
        .exec(http("Get book with ISBN ${ISBN}").get("/api/books/${ISBN}"))
    }

  setUp(scn.inject(atOnceUsers(10), rampUsers(50) over (30 seconds)))
    .assertions(global.responseTime.max.lessThan(5000))
    .protocols(conf)
}
```

---

# _Pipeline_-as-code

---

## Definition der Build-Pipeline per _Jenkinsfile_

```groovy
#!/usr/bin/env groovy

node {
    stage 'Checkout SCM'
    checkout scm

    stage 'Build/Analyse/Test'
    sh './gradlew clean build'
    archiveUnitTestResults()
    archiveDistributions()

    stage 'Dockerize'
    sh './gradlew buildDockerImage'

    stage 'Generate Documentation'
    sh './gradlew asciidoctor'
}
```

---

## Gradle und Docker to start Jenkins

```groovy
task createJenkinsHome() {
    mkdir("${System.getProperty('user.home')}/Jenkins")
}

task createJenkins(type: Exec, group: 'jenkinsci', dependsOn: createJenkinsHome, description: 'Create Jenkins') {
    commandLine 'docker', 'run', '--name', 'jenkinsci', '-p', '8080:8080', '-p', '50000:50000',
       '-v', "${System.getProperty('user.home')}/Jenkins:/var/jenkins_home", 'jenkinsci/jenkins'
}

task startJenkins(type: Exec, group: 'jenkinsci', description: 'Start Jenkins') {
    commandLine 'docker', 'start', 'jenkinsci'
}

task stopJenkins(type: Exec, group: 'jenkinsci', description: 'Stop Jenkins') {
    commandLine 'docker', 'stop', 'jenkinsci'
}
```

---

# _Infrastructure_-as-code

---

## Docker, Docker, Docker, ...

```
FROM qaware-oss-docker-registry.bintray.io/base/debian8-jre8
MAINTAINER M.-Leander Reimer <mario-leander.reimer@qaware.de>

RUN mkdir -p /app
ADD build/distributions/everything-as-code-1.2.0.tar /app

WORKDIR /app/everything-as-code-1.2.0
RUN chmod 755 bin/everything-as-code

EXPOSE 18080

CMD ./bin/everything-as-code
```

---

## Vagrant und Ruby zum Setup lokaler VMs

^ A little ruby knowledge is helpful, this is a Ruby DSL.

```ruby
require 'yaml'

$setup = <<SCRIPT
    sudo apt-add-repository ppa:ansible/ansible
    sudo apt-get update
    sudo apt-get install -y ansible sshpass
SCRIPT

Vagrant.configure("2") do |config|
    config.vm.box = "ubuntu/trusty32"

    settings = YAML.load_file 'src/vagrant/vagrant.yml'
    config.vm.provider "virtualbox" do |vb|
        vb.name = settings['vm']['name']
        vb.gui = false
        vb.memory = "512"
    end

    config.vm.provision "shell", inline: $setup
end
```

---

## Provisionierung mit Ansible (und Python)

^ You write YAML. Based on Python, good to know.
Also possible to use Ansible programmatically.
Good for classic IT, where you have no Docker.
But it can also be used for Docker or Kubernetes.

```yaml
---
# file: jenkinsci.yml
- hosts: jenkinsci
  remote_user: root
  tasks:
    - debug: msg="Creating a Jenkins pipeline job on {{ inventory_hostname }}"

    - jenkins_job:
        name: Everything-as-code Pipeline
        config: "{{ lookup('file', 'templates/pipeline-job.xml') }}"
        url: "http://{{ inventory_hostname }}"
        user: admin
        password: admin
```

---

## Cluster Orchestration mit Kubernetes

```yaml
---
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: everything-as-code
spec:
  replicas: 3
  template:
    metadata:
      labels:
        tier: backend
    spec:
      containers:
      - name: everything-as-code
        image: "qaware-oss-docker-registry.bintray.io/lreimer/everything-as-code:1.2.0"
        ports:
        - containerPort: 18080
        env:
        - name: PORT
          value: 18080
```

---

# _Documentation_-as-code

---
^ Design documentation, architecture documentation, ...

## Ja, wir brauchen Dokumentation!

- Und nein. Der Quellcode ist nicht genug!
- Technische Dokumente mit Word sind :anger: :skull: :pray:
- Dokumentation sollte neben dem Quellcode liegen: _change code, change docs_.
- Schnell und einfach zu schreiben.
- Unterstützung für Code, Bilder, Diagramme

---

^ Example architecture documentation using arc42

```asciidoc
// Beispiel Architektur-Dokumentation mit arc42 (https://arc42.github.io)

:imagesdir: ./images

= image:qaware-logo.png[QAware GmbH,2016] Everything-as-code
:toc-title: Table of Contents
:toc:

[[section-introduction-and-goals]]
== Introduction and Goals

The introduction to the architecture documentation should list the driving forces
that software architects must consider in their decisions.

=== Requirements Overview

=== Quality Goals

=== Stakeholders

<<<<
include::02_architecture_constraints.adoc[]

// further includes for the remaining sections
```

---

## AsciidoctorJ und Gradle to the Rescue
^ AsciidoctorJ is Asciidoctor on the JVM using JRuby.

```groovy
plugins { id "org.asciidoctor.convert" version "1.5.3" }

asciidoctorj { version = '1.5.4.1' }

asciidoctor {
    sourceDir 'src/docs/architecture'
    resources {
        from('src/docs/architecture') {
            include 'images/**/*.png'
            include 'images/**/*.jpg'
        }
    }
    backends 'html5'
    options doctype: 'article'
    attributes 'source-highlighter': 'coderay'
}
```

---

# _Presentation_-as-code

---
^ Alternatively use something like Reveal.js

## These slides were written in Markdown.

```markdown
---
## These slides were written in Markdown.

- This is for real programmers! :smiley:
- Several open source projects available
- Use HTML and JavaScript alternatively.

---
```

---

# _Heutige Projekte_ sind vielsprachig.

---
^ Few developers have the luxury (or tedium?) of working with a single technology.

# _Zeitgemäße_ Entwickler<br>müssen<br> _vielsprachig_ sein!

---

## Wir alle müssen unsere Hausaufgaben machen.

- **Entwickler**: Be polyglot, keep learning!
- **Architekten**: Die richtige Sprache ist stark abhängig vom jeweiligen Projekt-Kontext. Choose wisely!
- **Project Managers**: Give your techies freedom!
- **Universities**: Unterrichtet vielsprachig!

---

# *Fork me* on GitHub.
## https://github.com/lreimer/everything-as-code

---

# We are *hiring*.
## http://www.qaware.de/karriere/#jobs

---

# Q & A
