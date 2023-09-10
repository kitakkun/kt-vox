package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DictApiTest : BaseKtVoxApiTest() {

    @Test
    fun testGetDict() = runTest {
        api.getUserDict()
    }

    @Test
    fun testAddDictWord() = runTest {
        api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        )
    }

    @Test
    fun testRewriteDictWord() = runTest {
        val uuid = api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        )
        api.rewriteUserDictWord(
            wordUuid = uuid,
            surface = "こんにちは",
            pronunciation = "コンニチハハハハ",
            accentType = 0,
        )
    }

    @Test
    fun testDeleteDictWord() = runTest {
        val uuid = api.addUserDictWord(
            surface = "こんにちは",
            pronunciation = "コンニチハ",
            accentType = 0,
        )
        api.deleteUserDictWord(uuid)
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
        api.importUserDict(
            override = true,
            importDictData = userDict
        )
    }
}
