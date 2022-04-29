#!/usr/bin/env groovy

def imageName = "un4ckn0wl3z/java-maven-app"

def call() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: "docker-hub-credentials", passwordVariable: "PASS", usernameVariable: "USER")])
            {
                sh "docker build -t ${imageName}:dev-2.0 ."
                sh "echo $PASS | docker login -u $USER --password-stdin"
                sh "docker push ${imageName}:dev-2.0"
            }

}