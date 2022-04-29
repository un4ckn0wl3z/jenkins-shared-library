#!/usr/bin/env groovy

import com.unkclub.Docker

def call() {
    return new Docker(this).dockerLogin()
}