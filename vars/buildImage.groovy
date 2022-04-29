#!/usr/bin/env groovy

import com.unkclub.Docker

def call(String imageName, String version) {
    return new Docker(this).buildDockerImage(imageName, version)
}