package uk.co.littlemike.gradle.git

import groovy.mock.interceptor.StubFor
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
    void createsAnnotatedTagAndPushesForRelease() {
        project.pluginManager.apply GitTagPlugin
        project.tag.version = '1.2.3'
        project.tag.message = 'Release 1.2.3'
        project.tag.username = 'Username'
        project.tag.password = 'Password'
        project.tag.dryRunPush = true

        def git = new StubFor(Git)
        git.demand.with {
            tag { name, annotated, force, message ->
                assert name == project.tag.version
                assert annotated == true
                assert force == false
                assert message == project.tag.message
            }
            push { username, password, dryRun ->
                assert username == project.tag.username
                assert password == project.tag.password
                assert dryRun == project.tag.dryRunPush
            }
        }

        git.use {
            project.tasks.tag.execute()
        }
        git.verify()
    }

    @Test
    void createsLightweightTagForSnapshot() {
        project.pluginManager.apply GitTagPlugin
        project.tag.version = '1.2.3-SNAPSHOT'

        def git = new StubFor(Git)
        git.demand.with {
            tag { name, annotated, force ->
                assert name == project.tag.version
                assert annotated == false
                assert force == true
            }
        }

        git.use {
            project.tasks.tag.execute()
        }
        git.verify()
    }
}
