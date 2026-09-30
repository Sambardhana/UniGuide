val buildDirBase = file("${System.getProperty("user.home")}/.gradle-builds/${rootProject.name}")

layout.buildDirectory.set(file("$buildDirBase/root"))

subprojects {
    layout.buildDirectory.set(file("$buildDirBase/$name"))
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose.compiler) apply false
}