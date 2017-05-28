package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.DependenciesOverviewPlugin.Companion.DEPENDENCIES_OVERVIEW_TASK
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class DependenciesOverviewTaskTest {

    lateinit var task: DependenciesOverviewTask

    @Before
    fun setUp() {
        prepareTask()
    }

    @Test
    fun `task should be in documentation group`() = assertEquals("documentation", task.group)

    @Test
    fun `task has description`() = assertNotNull(task.description.isNotBlank())

    private fun prepareTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependenciesOverviewPlugin::class.java)
        task = project.tasks.findByPath(DEPENDENCIES_OVERVIEW_TASK) as DependenciesOverviewTask
    }
}

