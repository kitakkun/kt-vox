# KtVox

[![](https://jitpack.io/v/kitakkun/kt-vox.svg)](https://jitpack.io/#kitakkun/kt-vox)

KtVox is a wrapper library for the VOICEVOX API.
It is implemented as a Retrofit API.

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

- [x] POST `/audio_query`
- [x] POST `/audio_query_from_preset`

### Query Editing

- [x] POST `/accent_phrases`
- [x] POST `/mora_data`
- [x] POST `/mora_length`
- [x] POST `/mora_pitch`

### Synthesis

- [x] POST `/synthesis`
- [ ] POST `/cancellable_synthesis`
- [x] POST `/multi_synthesis`
- [x] POST `/morphable_targets`
- [x] POST `/synthesis_morphing`

### Extra

- [ ] POST `/connect_waves`
- [x] GET `/presets`
- [x] POST `/add_preset`
- [x] POST `/update_preset`
- [x] POST `/delete_preset`
- [x] GET `/version`
- [x] GET `/core_versions`
- [x] GET `/speakers`
- [x] GET `/speaker_info`
- [ ] GET `/downloadable_libraries`
- [ ] GET `/download_library`
- [x] POST `/initialize_speaker`
- [x] GET `/is_initialized_speaker`
- [x] GET `/supported_devices`
- [x] GET `/engine_manifest`

### User Dictionary

- [x] GET `/user_dict`
- [x] POST `/user_dict_word`
- [x] PUT `/user_dict_word/{word_uuid}`
- [x] DELETE `/user_dict_word/{word_uuid}`
- [ ] POST `/import_user_dict`

### Setting

- [ ] GET `/setting`
- [ ] POST `/setting`
