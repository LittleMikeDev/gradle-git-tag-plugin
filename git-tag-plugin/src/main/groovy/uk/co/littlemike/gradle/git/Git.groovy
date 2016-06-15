package uk.co.littlemike.gradle.git

import org.ajoberstar.grgit.Grgit
import org.ajoberstar.grgit.Remote

class Git {
    Grgit git = Grgit.open()

    void tag(String name, String message = '') {
        git.tag.add(
                name: name,
                message: message
        )
    }

    void push(boolean dryRun, String username = null, String password = null) {
        git.push(
                tags: true,
                dryRun: dryRun,
                remote: pushUrl(username, password)
        )
    }

    String pushUrl(String username, String password) {
        Remote remote = git.remote.list().first()
        def pushUrl = remote.pushUrl ?: remote.url
        if (username != null) {
            pushUrl = pushUrl.replaceFirst(/:\/\//, "://$username@")
        }
        if (password != null) {
            pushUrl = pushUrl.replaceFirst(/@/, ":$password@")
        }
        pushUrl
    }
}
