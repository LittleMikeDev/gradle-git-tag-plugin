package uk.co.littlemike.gradle.git

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitTagPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.create('tag', GitTagTask) {
            config = project.extensions.create('tag', GitTagConfig, project)
        }
    }
}
