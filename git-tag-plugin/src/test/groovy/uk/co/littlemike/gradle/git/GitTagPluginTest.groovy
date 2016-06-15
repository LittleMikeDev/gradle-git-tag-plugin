package uk.co.littlemike.gradle.git

import groovy.mock.interceptor.StubFor
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class GitTagPluginTest {

    def project = ProjectBuilder.builder().build()

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
            tag { name, message ->
                assert name == project.tag.version
                assert message == project.tag.message
            }
            push { dryRun, username, password ->
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

    @Test(expected = RuntimeException.class)
    void cannotTagSnapshotVersions() {
        project.pluginManager.apply GitTagPlugin
        project.tag.version = '1.2.3-SNAPSHOT'

        project.tasks.tag.execute()
    }
}
