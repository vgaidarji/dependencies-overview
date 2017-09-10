package com.vgaidarji.dependencies.overview

import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedModuleVersion

/**
 * Default project configuration from where artifacts will be resolved.
 */
const val DEFAULT_CONFIGURATION = "compile"

/**
 * Resolves artifacts for given project.
 */
class ArtifactsResolver(var project: Project) {
    /**
     * Resolves artifacts list for given configuration.
     * @param configuration default value is "compile" configuration
     */
    fun resolve(configuration: String = DEFAULT_CONFIGURATION): List<ResolvedModuleVersion> {
        val artifacts = mutableListOf<ResolvedModuleVersion>()
        val config = project.configurations.getByName(configuration).resolvedConfiguration
        config.resolvedArtifacts.forEach {
            artifacts.add(it.moduleVersion)
        }
        return sortArtifacts(artifacts)
    }

    /**
     * Sorts given artifacts list by artifact group and name.
     */
    private fun sortArtifacts(artifacts: List<ResolvedModuleVersion>): List<ResolvedModuleVersion> {
        return artifacts.sortedWith(compareBy({ it.id.group }, { it.id.name }))
    }
}
