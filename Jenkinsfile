#!/usr/bin/env groovy

def appName = "everything-as-code"
def project = ""

node {
    stage('Pipeline-as-code') {
        echo "The build pipeline for Everything-as-code."
        echo "Build ${env.BUILD_TAG}"
    }

    stage("Initialize") {
        project = env.PROJECT_NAME
    }

    stage('Checkout SCM') {
        checkout scm
    }

    stage('Build/Analyse/Test') {
        sh './gradlew clean build'
        archiveUnitTestResults()
        archiveDistributions()
        stash name: "tar", includes: "build/libs/everything-as-code-1.2.3.tar"
    }

    stage('Generate Documentation') {
        sh './gradlew asciidoctor'
    }

    // add additional stages to build docker image
    // run integration and performance tests
    // maybe add a stage to deploy the image to
    // the infrastructure

    /*
    stage("Build Docker Image") {
        unstash name: "tar"
        sh "oc start-build everything-as-code-image --from-file=build/libs/everything-as-code-1.2.3.tar -n ${project}"
        openshiftVerifyBuild bldCfg: "everything-as-code-image", namespace: project, waitTime: '20', waitUnit: 'min'
    }

    stage("Deploy to OpenShift") {
        openshiftDeploy deploymentConfig: appName, namespace: project
    }
    */

}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}

def archiveDistributions() {
    step([$class: 'ArtifactArchiver', artifacts: 'build/distributions/*.zip', fingerprint: true])
}
