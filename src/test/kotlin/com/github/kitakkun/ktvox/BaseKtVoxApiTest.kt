package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.module.ktVoxModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseKtVoxApiTest : KoinTest {
    private val imageName = DockerImageName.parse(
        "voicevox/voicevox_engine:cpu-ubuntu20.04-latest"
    )
    private lateinit var container: GenericContainer<Nothing>

    @BeforeTest
    fun setup() {
        container = GenericContainer<Nothing>(imageName)
            .withExposedPorts(50021)
        // FYI: https://hub.docker.com/layers/voicevox/voicevox_engine/cpu-ubuntu20.04-latest/images/sha256-f210f09d2307acbb0e95457dbe654c8f770df718432f5854b4064e2334c6b98c?context=explore
        container.setCommand(
            "gosu",
            "user",
            "/opt/python/bin/python3",
            "./run.py",
            "--voicelib_dir",
            "/opt/voicevox_core/",
            "--runtime_dir",
            "/opt/onnxruntime/lib",
            "--host",
            "0.0.0.0",
            "--enable_cancellable_synthesis", // experimental api (for test)
            "--enable_mock" // make tests run faster
        )
        container.start()
        val host = container.host
        val port = container.getMappedPort(50021)
        startKoin {
            modules(
                ktVoxModule(baseUrl = "http://$host:$port")
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        container.stop()
    }
}
