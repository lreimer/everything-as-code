#!/usr/bin/env groovy

node {
    stage 'Checkout SCM'
    checkout scm

    stage 'Build Everything'
    sh "./gradlew clean assemble"

    stage 'Run Unit Tests'
    sh "./gradlew build"
    archiveUnitTestResults()
    archiveDistributions()

    stage 'Build Docker Image'
    sh "./gradlew buildDockerImage"

    echo 'Generate Documentation'
    sh "./gradlew ascii"
}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}

def archiveDistributions() {
    step([$class: 'ArtifactArchiver', artifacts: 'build/distributions/*.*', fingerprint: true])
}