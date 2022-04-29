#!/usr/bin/env groovy
package com.unkclub
class Docker implements Serializable {
    def script

    Docker(script){
        this.script = script
    }

    def dockerLogin(){
        script.echo "Docker login!"
        script.withCredentials([script.usernamePassword(credentialsId: "docker-hub-credentials", passwordVariable: "PASS", usernameVariable: "USER")])
                {
                    script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                }
    }

    def buildDockerImage(String imageName, String version){
        script.echo "building the docker image..."
        script.sh "docker build -t ${imageName}:${version} ."
    }

    def dockerPushImage(String imageName, String version){
        script.sh "docker push ${imageName}:${version}"
    }
}