# glance-widget-demo

Android demo widget implemented with [Glance](https://developer.android.com/jetpack/androidx/releases/glance)

> [!NOTE]
>
> version androidx.glance:glance*:1.1.0 is used

## Features

- ✅ Stateful widget
- ✅ Handles user interaction
- ✅ Async operation on Worker
- ✅ Supports dark mode by Material3 color system
- ✅ Supports widget configuration (optional for Android 12 and above)
- ✅ Supports reconfiguration (only for Android 12 and above)
- ✅ Hilt with GlanceAppWidget and Worker
- ✅ Unit test for glance ui



Build app & widget
```shell
./gradlew app:assembleRelease
```

Run unit test
```shell
./gradlew widget:test
```


### widget overview

| user interaction                                                                                                        | dark mode                                                                                                                   |  
|-------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|  
|![async_glance](https://github.com/Seo-4d696b75/glance-widget-demo/assets/25225028/f0250095-3e51-47da-af41-a86527637c90) | ![tap_glance_dark](https://github.com/Seo-4d696b75/glance-widget-demo/assets/25225028/de8c82e8-25c8-4299-a95b-8a7f4ac29a0c) |  

### configuration

| configuration when added                                                                                                     | reconfiguration for existing                                                                                                  |  
|------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|  
| ![glance_configure](https://github.com/Seo-4d696b75/glance-widget-demo/assets/25225028/bc5ea08c-7656-4737-bf4a-fa2038fb0f6e) | ![glance_reconfigure](https://github.com/Seo-4d696b75/glance-widget-demo/assets/25225028/4a19ea9a-2a9e-4783-b6fd-6cb638a063ff) |  

## Modules structures

- `app`
- `theme` common Material3 color and theme definition for Jetpack Compose and Glance
- `ui` implementation with Jetpack Compose 
- `widget` implementation with Glance
- `domain` repository interface definition
- `data` repository implementation
