package com.vgaidarji.dependencies.overview

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesOverviewPlugin : Plugin<Project> {
    
    companion object {
        const val DEPENDENCIES_OVERVIEW_TASK = "dependenciesOverview"
    }

    override fun apply(project: Project) {
        project.tasks?.create(DEPENDENCIES_OVERVIEW_TASK, DependenciesOverviewTask::class.java)
    }
}
