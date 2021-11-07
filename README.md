# Rick-and-Morty 

Rick-and-Morty is a small demo application to test out and showcase integrations with various modern Android tech stacks and libraries.

## Tech-stacks & libraries used : 
* Minimum SDK level 22
* Completely written in [Kotlin](https://kotlinlang.org/).
* [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI toolkit for Android.
* [Hilt](https://dagger.dev/hilt/) for dependency injection.
* [Apollo](https://github.com/apollographql/apollo-android) - A strongly-typed `GraphQL` client.
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for pagination.
* [Room](https://developer.android.com/training/data-storage/room) for local data persistence.
* [Coil](https://github.com/coil-kt/coil) - Image loading library backed by Kotlin Coroutines.
* [Eventbus](https://github.com/greenrobot/EventBus) for inter-module communication.
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) & [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
* [Navigation component](https://developer.android.com/guide/navigation) for handling navigation between screens.


## Branches:
This project started off initially with the regular view based UI, and after completion of the UI and all of the business logic, 
piece by piece (module by module) migration to the Jetpack Compose implementation began and has completely been migrated to Jetpack Compose now, 
and all of the view-based code has been removed.

Each of these stages has been individually tracked in separate respective branches as follows 
* [view](https://github.com/JayaSuryaT/Rick-and-Morty/tree/view)
* [jetpack_compose_and_view](https://github.com/JayaSuryaT/Rick-and-Morty/tree/jetpack_compose_and_view)
* [jetpack_compose](https://github.com/JayaSuryaT/Rick-and-Morty/tree/jetpack_compose)


## View(Fragment) based implementation 

| Dark mode | Light mode | 
| -- | -- |
| <img src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-View-Dark.gif" alt="Using Views - Light theme" data-canonical-src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-View-Dark.gif" width="270" height="585" />|<img src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-View-Light.gif" alt="Using Views - Dark theme" data-canonical-src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-View-Light.gif" width="270" height="585" />|

## Jetpack Compose implementation

| Dark mode | Light mode |
| -- | -- |
| <img src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-JC-Dark.gif" alt="Using Jetpack Compose - Dark theme" data-canonical-src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-JC-Dark.gif" width="270" height="585" />| <img src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-JC-Light.gif" alt="Using Jetpack Compose - Light theme" data-canonical-src="https://github.com/JayaSuryaT/Rick-and-Morty/raw/jetpack_compose_and_view/gifs/Rick_and_Morty-JC-Light.gif" width="270" height="585" />|


## TODO
- [x] Migrate to `buildSrc`
- [x] Migrate to `GraphQl` client
- [x] Add pagination for 'list screens'
- [x] Add shared element transitions for view based implemtation
- [x] Enhance overall page animations for view based implemtation
- [x] Migrate to `Jetpack Compose`
- [ ] Improve router implementation
- [ ] Add animations to the `Jetpack Compose` implemtation
