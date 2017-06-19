package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.writer.JsonWriter
import com.vgaidarji.dependencies.overview.writer.MarkdownWriter
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.ResolvedModuleVersion
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {

    init {
        description = "Generates project dependencies overview table from project dependencies"
        group = "documentation"
    }

    @TaskAction
    fun generate() {
        JsonWriter().write(getArtifactsForConfiguration())
        MarkdownWriter().write(getArtifactsForConfiguration())
    }

    fun getArtifactsForConfiguration(configuration: String = "compile") :
            MutableList<ResolvedModuleVersion> {
        val artifacts = mutableListOf<ResolvedModuleVersion>()
        val config = project.configurations.getByName(configuration).resolvedConfiguration
        config.resolvedArtifacts.forEach {
            artifacts.add(it.moduleVersion)
        }
        return artifacts
    }
}

