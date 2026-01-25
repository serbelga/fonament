# Fonament Preferences

The fonament preferences is an API that provides functions to store and retrieve data from [DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore).

- `FonamentPreferences`: The class that provides functions to set and get data from a `DataStore<Preferences>` instance.
- `FonamentPreferencesFactory`: The factory for creating `FonamentPreferences` instances.
- `FonamentPreferencesDataStoreSingleton`: The class that creates a `DataStore` instance for a given file name as a singleton.

## Usage: without DI

You can crete a `FonamentPreferences` instance using the `FonamentPreferencesFactory` class.

```kotlin
val fonamentPreferences = FonamentPreferencesFactory().create("YOUR_DATASTORE_PREFERENCES_FILE_NAME")

// ℹ️ It is not necessary to specify the file format extension .preferences_pb in the create(name).
```

!!! info

    `FonamentPreferencesFactory` uses `FonamentPreferencesDataStoreSingleton` to create a `DataStore` instance for a given file name as a singleton to avoid:

    ```
    ❌ There are multiple DataStores active for the same file: ... 
    You should either maintain your DataStore as a singleton 
    or confirm that there is no two DataStore's active on the same file 
    (by confirming that the scope is cancelled).
    ```

!!! android

    `FonamentPreferencesFactory` needs a `Context` instance to create the `FonamentPreferences`.

    ```kotlin
    val fonamentPreferences = FonamentPreferencesFactory(context).create("YOUR_DATASTORE_PREFERENCES_FILE_NAME")
    ```


### Using `FonamentPreferences` instance

```kotlin
class UserPreferencesRepository(
    private val preferences: FonamentPreferences,
) {
    fun getUserTheme(): Flow<AppTheme> =
        preferences.getInt(APP_THEME_KEY).map { theme ->
            // ...
        }

    suspend fun setUserTheme(theme: AppTheme) {
        preferences[APP_THEME_KEY] = theme.ordinal
    }
}
```

## Usage: with DI

`:fonament-preferences:di` provides a subset of modules to create and inject a `FonamentPreferences` instance.

### Koin

Create a `FonamentPreferences` instance using [Koin](https://insert-koin.io/).

```kotlin
val preferencesModule = module {
    fonamentPreferences(name = "preferences_name")
}
```

!!! android

    Remember to inject the Android context via [androidContext](https://insert-koin.io/docs/reference/koin-android/start/).
