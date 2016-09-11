#!/usr/bin/env groovy

node {
    stage 'Checkout SCM'
    checkout scm

    stage 'Build/Analyse/Test'
    sh './gradlew clean build'
    archiveUnitTestResults()
    archiveDistributions()

    echo 'Generate Documentation'
    sh './gradlew asciidoctor'
}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}

def archiveDistributions() {
    step([$class: 'ArtifactArchiver', artifacts: 'build/distributions/*.*', fingerprint: true])
}