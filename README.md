# Marvel Composable
Application that uses the Marvel API with Jetpack Compose and Kotlin Ecosystem. Implementing advanced concepts such as navigation, pagination and animations

## Screen shots

<p align="center">
 <img src="https://github.com/santimattius/android-marvel-app/blob/master/screenshots/marvel_app_home.png?raw=true" alt="Detail screen capture" width="200"/>
 <img src="https://github.com/santimattius/android-marvel-app/blob/master/screenshots/marvel_app_detail.png?raw=true" alt="Detail screen capture" width="200"/>
</p>

## Dependencies
- **Jetpack compose**: 
  - https://developer.android.com/jetpack/compose
- **Paging 3.0 with compose support**:
  - https://developer.android.com/topic/libraries/architecture/paging/v3-overview
- **Jetpack compose navigation**:
  -  https://developer.android.com/jetpack/compose/navigation?hl=es-419
- **Koin** with dependencie injector: 
  - https://insert-koin.io/
- **Ktor** with service client implementation: 
  - https://kotlinlang.org/docs/kmm-getting-started.html
- **Kotlin Serialization** on json parser:
  - https://github.com/Kotlin/kotlinx.serialization
- **Coil Compose** with image loader:
  - https://coil-kt.github.io/coil/compose/
- **Kotlin coroutines** and **Kotlion Flow**
  - https://kotlinlang.org/docs/reference/coroutines-overview.html
  - https://kotlinlang.org/docs/reference/coroutines/flow.html
 
 ## Marvel API

Marvel developers: https://developer.marvel.com/

Add in the local.properties file the following variables **marvelPrivateKey** and **marvelPublicKey** with the keys obtained in the marvel api portal and then configure add the following code in the build.gradle of the marvel-client module:

 ``` groovy
 
def localProperties = new Properties()
localProperties.load(new FileInputStream(rootProject.file("local.properties")))

android {
    compileSdk 31

    defaultConfig {
        ...

        buildConfigField "String", "PRIVATE_KEY", localProperties['marvelPrivateKey']
        buildConfigField "String", "PUBLIC_KEY", localProperties['marvelPublicKey']
    }
  ...
 }
    
 ```


