package uk.co.littlemike.gradle.git

import org.ajoberstar.grgit.Grgit
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Ignore
import org.junit.Test

class GitTagPluginIntegrationTest {

    def version = UUID.randomUUID().toString()
    def project = ProjectBuilder.builder().build()

    @After
    void deleteTag() {
        Grgit.open().tag.remove(names: [version])
    }

    @Ignore("Shouldn't be run in CI as requires auth, for manual testing only")
    @Test
    void appliesPluginAndPushes() {
        project.pluginManager.apply GitTagPlugin

        project.tag.version = version
        project.tag.dryRunPush = true

        project.tasks.tag.execute()
    }
}
