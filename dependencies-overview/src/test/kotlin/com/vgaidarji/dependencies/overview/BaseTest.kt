package com.vgaidarji.dependencies.overview

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.whenever
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.ResolvedConfiguration
import org.gradle.testfixtures.ProjectBuilder

open class BaseTest {
    protected fun projectWithArtifacts(vararg artifacts: TestArtifact): Project {
        val project: Project = spy(ProjectBuilder.builder().build())
        val configurationContainer = com.nhaarman.mockito_kotlin.mock<ConfigurationContainer>()
        val configuration = com.nhaarman.mockito_kotlin.mock<Configuration>()
        val resolvedConfiguration = com.nhaarman.mockito_kotlin.mock<ResolvedConfiguration>()
        val resolvedArtifacts = artifacts.map { it.resolvedArtifact }.toSet()
        whenever(resolvedConfiguration.resolvedArtifacts).thenReturn(resolvedArtifacts)
        whenever(configuration.resolvedConfiguration).thenReturn(resolvedConfiguration)
        whenever(configurationContainer.getByName(any())).thenReturn(configuration)
        whenever(project.configurations).thenReturn(configurationContainer)
        return project
    }
}