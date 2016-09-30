# TQ1 Demo Android
This application should be used as the primary sample of integration with TQ1 and using either [TQG](https://sites.google.com/a/taqtile.com/documentacao-taqtile/tqg-documentation?pli=1).

# Configuration

### App and TQ1/TQG Keys:

You can change the default configurations for this application on the file Constants.java.

### Updating SDK Versions:

This values are set on app-level build.gradle file in `dependencies`:

- TQ1 SDK dependency, currently in version **3.4.1**
```
    compile 'com.taqtile:android-sdk:3.4.1'
```
- TQG SDK dependency, currently in version **0.9.0**
```
    compile 'com.taqtile:tqg-android-sdk:0.9.0'
```

### Development Environment

- The app was entirely developed using **Android Studio 2.1**.
- The minimum SDK version to run the project is **14**.

### TQ1/TQG Documentation
TQ1 and TQG have their own documentation, you can access it by the following links:

- [TQ1](http://tq1-android-sdk.readthedocs.io/en/master/docs/).
- [TQG](http://tqg-android-sdk.readthedocs.io/en/master-android/).
