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
