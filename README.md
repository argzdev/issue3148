# issue3148
### What product is affected?
- Firebase Crashlytics - Kotlin DSL
### Description
- Issue 3114: [link](https://github.com/firebase/firebase-android-sdk/issues/3148)
- Issue 2444: [link](https://github.com/firebase/firebase-android-sdk/issues/2444) - Related
- Issue 2665: [link](https://github.com/firebase/firebase-android-sdk/issues/2665) - Related
### Summary
- Plugin Extension is not registered on versions `2.6.0+`
- Developer made a gradle task that prints out all extensions
- `CrashlyticsExtension` is only visible on when using version `2.5.2`
### Steps to reproduce issue
- Clone project
- Open project
- Go to Terminal
- Go to /{project}/build.gradle
  - Scenario 1
    - Uncomment classpath `com.google.firebase:firebase-crashlytics-gradle` version `2.8.0`
    - Go to /{project}/app/build.gradle.kts
    - Uncomment [line 41 - 51] and print out the extensions 
    - See that `firebaseCrashlytics` is not included in the extensions
  - Scenario 2
    - Uncomment classpath `com.google.firebase:firebase-crashlytics-gradle` version `2.8.0`
    - Uncomment [line 79 - 94]
    - See error `CrashlyticsExtension` is unresolve
    
