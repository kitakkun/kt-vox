# KtVox

[![](https://jitpack.io/v/kitakkun/kt-vox.svg)](https://jitpack.io/#kitakkun/kt-vox)

KtVox is a wrapper library for the VOICEVOX API, implemented as a Retrofit API.

## Installation

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.kitakkun:kt-vox:$version")
}
```

## Usage

### Create a KtVoxApi instance

```kotlin
val ktVoxApi = KtVoxApi.initialize("http://127.0.0.1:50021")
```

### Call API

Note: You have to call the API in a coroutine scope.

```kotlin
launch {
    val response = api.createAudioQuery(
        text = "こんにちは",
        speaker = 0,
    )
    if (response.isSuccessful) {
        val audioQuery = response.body()
        println(audioQuery)
    } else {
        println(response.errorBody())
    }
}
```

## Supported APIs

### Query Creation

- :white_check_mark: POST `/audio_query`
- :white_check_mark: POST `/audio_query_from_preset`

### Query Editing

- :white_check_mark: POST `/accent_phrases`
- :white_check_mark: POST `/mora_data`
- :white_check_mark: POST `/mora_length`
- :white_check_mark: POST `/mora_pitch`

### Synthesis

- :white_check_mark: POST `/synthesis`
- :white_check_mark: POST `/cancellable_synthesis`
- :white_check_mark: POST `/multi_synthesis`
- :white_check_mark: POST `/morphable_targets`
- :white_check_mark: POST `/synthesis_morphing`

### Extra

- :white_large_square: POST `/connect_waves`
- :white_check_mark: GET `/presets`
- :white_check_mark: POST `/add_preset`
- :white_check_mark: POST `/update_preset`
- :white_check_mark: POST `/delete_preset`
- :white_check_mark: GET `/version`
- :white_check_mark: GET `/core_versions`
- :white_check_mark: GET `/speakers`
- :white_check_mark: GET `/speaker_info`
- :white_large_square: GET `/downloadable_libraries`
- :white_large_square: GET `/download_library`
- :white_check_mark: POST `/initialize_speaker`
- :white_check_mark: GET `/is_initialized_speaker`
- :white_check_mark: GET `/supported_devices`
- :white_check_mark: GET `/engine_manifest`

### User Dictionary

- :white_check_mark: GET `/user_dict`
- :white_check_mark: POST `/user_dict_word`
- :white_check_mark: PUT `/user_dict_word/{word_uuid}`
- :white_check_mark: DELETE `/user_dict_word/{word_uuid}`
- :white_check_mark: POST `/import_user_dict`

### Setting

- :white_check_mark: GET `/setting`
- :white_check_mark: POST `/setting`
