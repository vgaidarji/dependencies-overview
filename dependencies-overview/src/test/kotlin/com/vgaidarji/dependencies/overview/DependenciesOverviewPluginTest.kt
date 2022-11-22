package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.DependenciesOverviewPlugin.Companion.DEPENDENCIES_OVERVIEW_TASK
import junit.framework.TestCase.assertTrue
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class DependenciesOverviewPluginTest {
    @Test
    fun `plugin should create task when applied`() {
        val project = ProjectBuilder.builder().build()
        with(project) {
            pluginManager.apply(DependenciesOverviewPlugin::class.java)

            assertTrue(tasks.findByName(DEPENDENCIES_OVERVIEW_TASK) is DependenciesOverviewTask)
        }
    }
}
