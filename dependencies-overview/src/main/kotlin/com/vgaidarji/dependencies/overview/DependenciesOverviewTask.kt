package com.vgaidarji.dependencies.overview

import groovy.json.JsonOutput
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
        println(asJson(getArtifactsForConfiguration()))
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

    private fun asJson(artifacts: MutableList<ResolvedModuleVersion>): String {
        var json = "{\n\"dependencies\": ["
        artifacts.forEachIndexed { index, resolvedModuleVersion ->
            // println "group: ${id.group}, name: ${id.name}, version: ${id.version}"
            if (index > 0) json = json.plus(",")
            json = json.plus(JsonOutput.toJson(resolvedModuleVersion.id))
        }
        return JsonOutput.prettyPrint(json.plus("]\n}"))
    }
}
