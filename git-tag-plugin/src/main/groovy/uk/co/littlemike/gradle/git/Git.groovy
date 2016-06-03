package uk.co.littlemike.gradle.git

import org.ajoberstar.grgit.Grgit

class Git {
    Grgit git = Grgit.open()

    void tag(String name, boolean annotated, boolean force, String message = '') {
        git.tag.add(
                name: name,
                annotate: annotated,
                force: force,
                message: message
        )
    }

    void push(String username, String password, boolean dryRun) {
        // TODO set username and password if supplied
        git.push(
                tags: true,
                dryRun: dryRun
        )
    }
}
