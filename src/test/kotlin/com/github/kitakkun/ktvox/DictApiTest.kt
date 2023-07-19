package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.dictionary.DictApi
import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
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

    @Test
    fun testImportUserDict() = runTest {
        val userDict = mapOf(
            "e2acc2ef-e08b-42d3-9bab-b79e51c458c7" to UserDictWord(
                surface = "こんにちは",
                priority = 5,
                contextId = 1348,
                partOfSpeech = "名詞",
                partOfSpeechDetail1 = "固有名詞",
                partOfSpeechDetail2 = "一般",
                partOfSpeechDetail3 = "*",
                inflectionalType = "*",
                inflectionalForm = "*",
                stem = "*",
                yomi = "コンニチハ",
                pronunciation = "コンニチハ",
                accentType = 0,
                moraCount = 5,
                accentAssociativeRule = "*",
            )
        )
        val response = api.importUserDict(
            override = true,
            importDictData = userDict
        )
        assert(response.isSuccessful)
    }
}
