#!/usr/bin/env groovy
package com.unkclub
class Docker implements Serializable {
    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageName, String version){
        script.echo "building the docker image..."
        script.withCredentials([script.usernamePassword(credentialsId: "docker-hub-credentials", passwordVariable: "PASS", usernameVariable: "USER")])
                {
                    script.sh "docker build -t ${imageName}:${version} ."
                    script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                    script.sh "docker push ${imageName}:${version}"
                }
    }
}