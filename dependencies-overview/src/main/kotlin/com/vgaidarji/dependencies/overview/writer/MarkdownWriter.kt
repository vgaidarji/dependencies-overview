package com.vgaidarji.dependencies.overview.writer

import net.steppschuh.markdowngenerator.table.Table
import org.gradle.api.artifacts.ResolvedModuleVersion

class MarkdownWriter: DependenciesWriter<MutableList<ResolvedModuleVersion>> {

    companion object {
        const val OUTPUT_FILE_NAME = "DEPENDENCIES-OVERVIEW.md"
        const val GROUP = "Group"
        const val NAME = "Name"
        const val VERSION = "Version"
    }

    override fun write(artifacts: MutableList<ResolvedModuleVersion>) {
        val builder = Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow(GROUP, NAME, VERSION)
        artifacts.forEach {
            builder.addRow(it.id.group, it.id.name, it.id.version)
        }
        // TODO parametrize task and print to console conditionally
        writeToFile(OUTPUT_FILE_NAME, builder.build().toString())
    }
}
