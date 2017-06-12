package com.vgaidarji.dependencies.overview.writer

interface DependenciesWriter<in T> {
    fun write(artifacts: T)
}