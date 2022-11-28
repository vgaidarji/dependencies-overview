package com.vgaidarji.dependencies.overview

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.ResolvedConfiguration
import org.gradle.testfixtures.ProjectBuilder
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever

open class BaseTest {
    protected fun projectWithArtifacts(vararg artifacts: TestArtifact): Project {
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
