package uk.co.littlemike.gradle.git

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class GitTagPluginTest {

    Project project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
    }

    @Test
    void canBeAppliedToProject() {
        project.pluginManager.apply GitTagPlugin.class
    }
}
