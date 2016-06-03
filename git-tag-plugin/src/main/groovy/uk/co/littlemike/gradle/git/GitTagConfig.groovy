package uk.co.littlemike.gradle.git

import org.gradle.api.Project

class GitTagConfig {
    final Project project

    String version
    String message
    String username
    String password
    boolean dryRunPush = false

    GitTagConfig(Project project) {
        this.project = project
    }

    String getVersion() {
        version ?: project.version
    }

    String getMessage() {
        message ?: "Release ${getVersion()}"
    }
}
