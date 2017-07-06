package com.vgaidarji.dependencies.overview.writer

import groovy.json.JsonOutput
import org.gradle.api.artifacts.ResolvedModuleVersion

class JsonWriter: DependenciesWriter<MutableList<ResolvedModuleVersion>> {

    companion object {
        const val OUTPUT_FILE_NAME = "DEPENDENCIES-OVERVIEW.json"
        const val PARENT_TAG = "dependencies"
    }

    override fun write(artifacts: MutableList<ResolvedModuleVersion>) {
        // TODO parametrize task and print to console conditionally
        writeToFile(OUTPUT_FILE_NAME, artifactsToJson(artifacts))
    }

    private fun artifactsToJson(artifacts: MutableList<ResolvedModuleVersion>): String {
        var json = "{\n\"$PARENT_TAG\": ["
        artifacts.forEachIndexed { index, resolvedModuleVersion ->
            if (index > 0) json = json.plus(",")
            json = json.plus(JsonOutput.toJson(resolvedModuleVersion.id))
        }
        return JsonOutput.prettyPrint(json.plus("]\n}"))
    }
}