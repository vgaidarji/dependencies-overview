package com.vgaidarji.dependencies.overview

import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedModuleVersion

/**
 * Default project configuration from where artifacts will be resolved.
 */
const val DEFAULT_CONFIGURATION = "implementation"

/**
 * Resolves artifacts for given project.
 */
class ArtifactsResolver(var project: Project) {
    /**
     * Resolves artifacts list for given configuration.
     * @param configuration default value is "implementation" configuration
     */
    fun resolve(configuration: String = DEFAULT_CONFIGURATION): List<ResolvedModuleVersion> {
        // https://discuss.gradle.org/t/what-is-a-configuration-which-cant-be-directly-resolved/30721/2
        try {
            project.configurations.getByName(configuration).isCanBeResolved = true
        } catch (_: Exception) {}
        val config = project.configurations.getByName(configuration).resolvedConfiguration
        val artifacts = mutableListOf<ResolvedModuleVersion>()
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
