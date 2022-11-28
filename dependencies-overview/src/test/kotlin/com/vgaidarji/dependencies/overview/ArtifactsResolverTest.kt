package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_ANDROID_PLAY_SERVICES_ADS
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_ANDROID_PLAY_SERVICES_BASE
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_GSON
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_GUAVA
import com.vgaidarji.dependencies.overview.TestArtifact.JODA_TIME
import com.vgaidarji.dependencies.overview.TestArtifact.SLF4J
import junit.framework.TestCase.assertTrue
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.junit.Test

class ArtifactsResolverTest : BaseTest() {

    @Test
    fun `resolves artifacts for default configuration`() {
        val resolver = ArtifactsResolver(
            projectWithArtifacts(JODA_TIME, SLF4J)
        )

        val artifacts = resolver.resolve()

        artifacts shouldContain JODA_TIME.moduleVersion
        artifacts shouldContain SLF4J.moduleVersion
        artifacts shouldNotContain GOOGLE_GSON.moduleVersion
    }

    @Test
    fun `resolved artifacts are sorted by group and name`() {
        val sortedArtifacts = listOf(
            GOOGLE_ANDROID_PLAY_SERVICES_ADS,
            GOOGLE_ANDROID_PLAY_SERVICES_BASE,
            GOOGLE_GSON,
            GOOGLE_GUAVA,
            JODA_TIME,
            SLF4J
        )
        val sortedArtifactsModules = sortedArtifacts.map { it.moduleVersion }
        val resolver = ArtifactsResolver(
            projectWithArtifacts(
                GOOGLE_ANDROID_PLAY_SERVICES_BASE,
                JODA_TIME,
                GOOGLE_GUAVA,
                SLF4J,
                GOOGLE_ANDROID_PLAY_SERVICES_ADS,
                GOOGLE_GSON
            )
        )

        val artifacts = resolver.resolve()

        // "com.google.android.gms:play-services-ads"
        // "com.google.android.gms:play-services-base"
        // "com.google.code.gson:gson"
        // "com.google.guava:guava"
        // "joda-time:joda-time"
        // "org.slf4j:slf4j-api"
        sortedArtifactsModules.forEachIndexed { index, version ->
            val expectation = "Expected artifact ${version.id.group}:${version.id.name} at $index, " +
                "but was ${artifacts[index].id.group}:${artifacts[index].id.name}"
            assertTrue(expectation, artifacts[index] == version)
        }
    }
}
