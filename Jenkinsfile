#!/usr/bin/env groovy

node {
    stage 'Pipeline-as-code'
    echo "The build pipeline for Everything-as-code."
    echo "${env.BUILD_ID}"
    echo "${env.BUILD_TAG}"

    stage 'Checkout SCM'
    checkout scm

    stage 'Devoxx Stage'
    echo "Hello Devoxx Poland demo hope you are fine."
    
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
    step([$class: 'ArtifactArchiver', artifacts: 'build/distributions/*.zip', fingerprint: true])
}
