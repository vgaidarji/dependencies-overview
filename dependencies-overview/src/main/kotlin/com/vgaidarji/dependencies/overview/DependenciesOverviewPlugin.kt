package com.vgaidarji.dependencies.overview

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesOverviewPlugin : Plugin<Project> {
    
    companion object {
        const val DEPENDENCIES_OVERVIEW_TASK = "dependenciesOverview"
        const val EXTENSION = "dependenciesOverview"
    }

    override fun apply(project: Project) {
        project.extensions?.create(EXTENSION, DependenciesOverviewExtension::class.java)
        project.tasks?.create(DEPENDENCIES_OVERVIEW_TASK, DependenciesOverviewTask::class.java)
    }
}
