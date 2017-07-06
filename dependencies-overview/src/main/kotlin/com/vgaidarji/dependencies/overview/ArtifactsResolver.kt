package com.vgaidarji.dependencies.overview

import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedModuleVersion

/**
 * Resolves artifacts for given project.
 */
class ArtifactsResolver(var project: Project) {
    /**
     * Resolves artifacts list for given configuration.
     * @param configuration default value is "compile" configuration
     */
    fun resolve(configuration: String = "compile") :
        MutableList<ResolvedModuleVersion> {
        val artifacts = mutableListOf<ResolvedModuleVersion>()
        val config = project.configurations.getByName(configuration).resolvedConfiguration
        config.resolvedArtifacts.forEach {
            artifacts.add(it.moduleVersion)
        }
        return artifacts
    }
}
