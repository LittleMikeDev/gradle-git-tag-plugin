package uk.co.littlemike.gradle.git

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class GitTagConfigTest {

    Project project

    @Before
    void setUp() {
        project = new ProjectBuilder().build()
    }

    @Test
    void usesProjectVersionByDefault() {
        project.pluginManager.apply GitTagPlugin
        project.version = '1.2.3'

        assert project.tag.version == '1.2.3'
        assert project.tag.message == 'Release 1.2.3'
    }

    @Test
    void includesOverriddenVersionInMessage() {
        project.pluginManager.apply GitTagPlugin
        project.tag.version = '1.0'

        assert project.tag.message == 'Release 1.0'
    }
}
