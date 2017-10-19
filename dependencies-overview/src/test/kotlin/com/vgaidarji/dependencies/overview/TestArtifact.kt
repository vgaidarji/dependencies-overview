package com.vgaidarji.dependencies.overview

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.ModuleVersionIdentifier
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.artifacts.ResolvedModuleVersion

enum class TestArtifact(val group: String, val artifactName: String, val version: String) {
    JODA_TIME("joda-time", "joda-time", "2.3"),
    SLF4J("org.slf4j", "slf4j-api", "1.7.7"),
    GOOGLE_GSON("com.google.code.gson", "gson", "2.2.4"),
    GOOGLE_GUAVA("com.google.guava", "guava", "16.0.1"),
    GOOGLE_ANDROID_PLAY_SERVICES_BASE("com.google.android.gms", "play-services-base", "11.0.0"),
    GOOGLE_ANDROID_PLAY_SERVICES_ADS("com.google.android.gms", "play-services-ads", "11.0.0");

    var resolvedArtifact: ResolvedArtifact
    var moduleVersion: ResolvedModuleVersion

    init {
        resolvedArtifact = toResolvedArtifact()
        moduleVersion = resolvedArtifact.moduleVersion
    }

    private fun toResolvedArtifact(moduleGroup: String = "",
        moduleName: String = ""): ResolvedArtifact {
        val resolvedArtifact = mock<ResolvedArtifact>()
        val moduleVersion = mock<ResolvedModuleVersion>()
        whenever(moduleVersion.id).thenReturn(
            createModuleVersionIdentifier(group, artifactName, version,
                moduleGroup, moduleName)
        )
        whenever(resolvedArtifact.moduleVersion).thenReturn(moduleVersion)
        return resolvedArtifact
    }

    private fun createModuleVersionIdentifier(group: String, name: String, version: String,
        moduleGroup: String = "", moduleName: String = ""): ModuleVersionIdentifier {
        return object : ModuleVersionIdentifier {
            override fun getGroup(): String {
                return group
            }

            override fun getVersion(): String {
                return version
            }

            override fun getName(): String {
                return name
            }

            override fun getModule(): ModuleIdentifier {
                return object : ModuleIdentifier {
                    override fun getGroup(): String {
                        return moduleGroup
                    }

                    override fun getName(): String {
                        return moduleName
                    }
                }
            }
        }
    }
}