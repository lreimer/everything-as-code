#!/usr/bin/env groovy

node {
    stage 'Checkout SCM'
    checkout scm

    stage 'Build/Analyse/Test'
    sh './gradlew clean build'
    archiveUnitTestResults()
    archiveDistributions()

    // add additional stages to build docker image
    // run integration and performance tests

    // maybe add a stage to deploy the image to
    // the infrastructure

    stage 'Generate Documentation'
    sh './gradlew asciidoctor'
}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}

def archiveDistributions() {
    step([$class: 'ArtifactArchiver', artifacts: 'build/distributions/*.*', fingerprint: true])
}