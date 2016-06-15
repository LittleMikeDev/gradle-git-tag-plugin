package uk.co.littlemike.gradle.git

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class GitTagTask extends DefaultTask {

    GitTagConfig config

    @TaskAction
    void tag() {
        def git = new Git()
        if (config.version.endsWith('-SNAPSHOT')) {
            throw new RuntimeException("Cannot tag snapshot versions")
        } else {
            git.tag(config.version, config.message)
            git.push(config.dryRunPush, config.username, config.password)
        }
    }
}
