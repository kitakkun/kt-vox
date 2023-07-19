package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.dictionary.DictApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.test.inject

class DictApiTest : BaseKtVoxApiTest() {
    private val api: DictApi by inject()

    @Test
    fun testGetDict() = runTest {
        val response = api.getUserDict()
        assert(response.isSuccessful)
    }

    @Test
    fun testAddDictWord() = runTest {
        val response = api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testRewriteDictWord() = runTest {
        val uuid = api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        ).body() ?: throw Exception("uuid is null")
        val response = api.rewriteUserDictWord(
            wordUuid = uuid,
            surface = "こんにちは",
            pronunciation = "コンニチハハハハ",
            accentType = 0,
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testDeleteDictWord() = runTest {
        val uuid = api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        ).body() ?: throw Exception("uuid is null")
        val response = api.deleteUserDictWord(uuid)
        assert(response.isSuccessful)
    }
}
