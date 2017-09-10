package com.vgaidarji.dependencies.overview

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.whenever
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_ANDROID_PLAY_SERVICES_ADS
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_ANDROID_PLAY_SERVICES_BASE
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_GSON
import com.vgaidarji.dependencies.overview.TestArtifact.GOOGLE_GUAVA
import com.vgaidarji.dependencies.overview.TestArtifact.JODA_TIME
import com.vgaidarji.dependencies.overview.TestArtifact.SLF4J
import org.amshove.kluent.shouldContain
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.ResolvedConfiguration
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import junit.framework.TestCase.assertTrue
import org.amshove.kluent.shouldNotContain

class ArtifactsResolverTest {

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
                SLF4J)
        val sortedArtifactsModules = sortedArtifacts.map { it.moduleVersion }
        val resolver = ArtifactsResolver(
                projectWithArtifacts(GOOGLE_ANDROID_PLAY_SERVICES_BASE, JODA_TIME,
                        GOOGLE_GUAVA, SLF4J, GOOGLE_ANDROID_PLAY_SERVICES_ADS, GOOGLE_GSON)
        )

        val artifacts = resolver.resolve()

        // "com.google.android.gms:play-services-ads"
        // "com.google.android.gms:play-services-base"
        // "com.google.code.gson:gson"
        // "com.google.guava:guava"
        // "joda-time:joda-time"
        // "org.slf4j:slf4j-api"
        sortedArtifactsModules.forEachIndexed { index, version ->
            assertTrue("Expected artifact ${version.id.group}:${version.id.name} at $index, "
                    + "but was ${artifacts[index].id.group}:${artifacts[index].id.name}",
                    artifacts[index] == version)
        }
    }

    private fun projectWithArtifacts(vararg artifacts: TestArtifact): Project {
        val project: Project = spy(ProjectBuilder.builder().build())
        val configurationContainer = mock<ConfigurationContainer>()
        val configuration = mock<Configuration>()
        val resolvedConfiguration = mock<ResolvedConfiguration>()
        val resolvedArtifacts = artifacts.map { it.resolvedArtifact }.toSet()
        whenever(resolvedConfiguration.resolvedArtifacts).thenReturn(resolvedArtifacts)
        whenever(configuration.resolvedConfiguration).thenReturn(resolvedConfiguration)
        whenever(configurationContainer.getByName(any())).thenReturn(configuration)
        whenever(project.configurations).thenReturn(configurationContainer)
        return project
    }
}