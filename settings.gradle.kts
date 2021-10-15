dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RickAndMorty"

include(":app")

include(":data")

include(":ui:base")

include(":ui:home")

include(":ui:character:characterList")
include(":ui:character:characterDetails")

include(":ui:episode:episodeList")
include(":ui:episode:episodeDetails")
include(":base-data")
