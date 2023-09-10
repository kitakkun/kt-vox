package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.KtVoxApi
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.utility.DockerImageName
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseKtVoxApiTest {
    private val imageName = DockerImageName.parse(
        "voicevox/voicevox_engine:cpu-ubuntu20.04-0.14.5"
    )
    private lateinit var container: GenericContainer<*>
    lateinit var api: KtVoxApi

    @BeforeTest
    fun setup() {
        container = GenericContainer(imageName)
            .withExposedPorts(50021)
            .withLogConsumer { outputFrame ->
                // for debugging test
                if (outputFrame.type == OutputFrame.OutputType.STDOUT) {
                    error(outputFrame.utf8String)
                }
            }
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
        // set presets.yaml writable to pass the tests.
        container.execInContainer("chmod", "-R", "777", "/opt/voicevox_engine/presets.yaml")
        val host = container.host
        val port = container.getMappedPort(50021)
        api = KtVoxApi.initialize("http://$host:$port/")
    }

    @AfterTest
    fun tearDown() {
        container.stop()
    }
}
