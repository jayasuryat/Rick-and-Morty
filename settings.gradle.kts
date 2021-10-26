dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RickAndMorty"

include(":app")

include(":ui-base")

include(":ui-home")

include(":ui-character-list")
include(":ui-character-details")

include(":ui-episode-list")
include(":ui-episode-details")
include(":base-data")
include(":ui-location-list")
include(":ui-location-details")
