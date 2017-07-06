package com.vgaidarji.dependencies.overview

open class DependenciesOverviewExtension {
    var output = OutputFormat()

    open class OutputFormat {
        var markdown = true
        var json = false
    }
}