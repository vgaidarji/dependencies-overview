package com.vgaidarji.dependencies.overview.writer

import net.steppschuh.markdowngenerator.table.Table
import org.gradle.api.artifacts.ResolvedModuleVersion

class MarkdownWriter: DependenciesWriter<MutableList<ResolvedModuleVersion>> {
    override fun write(artifacts: MutableList<ResolvedModuleVersion>) {
        val builder = Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Dependency", "Version")
        artifacts.forEachIndexed { index, module ->
            builder.addRow("${module.id.group}:${module.id.name}", module.id.version)
        }
        // TODO parametrize task and print to console conditionally
        writeToFile("DEPENDENCIES-OVERVIEW.md", builder.build().toString())
    }
}
