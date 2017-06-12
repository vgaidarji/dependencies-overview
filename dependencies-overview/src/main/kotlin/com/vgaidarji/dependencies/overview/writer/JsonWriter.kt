package com.vgaidarji.dependencies.overview.writer

import groovy.json.JsonOutput
import org.gradle.api.artifacts.ResolvedModuleVersion
import java.io.File

class JsonWriter: DependenciesWriter<MutableList<ResolvedModuleVersion>> {
    override fun write(artifacts: MutableList<ResolvedModuleVersion>) {
        val json = artifactsToJson(artifacts)
        writeToJsonFile(json)
    }

    private fun artifactsToJson(artifacts: MutableList<ResolvedModuleVersion>): String {
        var json = "{\n\"dependencies\": ["
        artifacts.forEachIndexed { index, resolvedModuleVersion ->
            // println "group: ${id.group}, name: ${id.name}, version: ${id.version}"
            if (index > 0) json = json.plus(",")
            json = json.plus(JsonOutput.toJson(resolvedModuleVersion.id))
        }
        return JsonOutput.prettyPrint(json.plus("]\n}"))
    }

    private fun writeToJsonFile(json: String) {
        File("DEPENDENCIES-OVERVIEW.json").printWriter().use { out ->
            out.println(json)
        }
    }
}