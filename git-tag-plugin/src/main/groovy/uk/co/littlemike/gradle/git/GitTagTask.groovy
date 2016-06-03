package uk.co.littlemike.gradle.git

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class GitTagTask extends DefaultTask {

    GitTagConfig config

    @TaskAction
    void tag() {
        def git = new Git()
        if (config.version.endsWith('-SNAPSHOT')) {
            git.tag(config.version, false, true)
        } else {
            git.tag(config.version, true, false, config.message)
            git.push(config.username, config.password, config.dryRunPush)
        }
    }
}
