import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.shared)
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.material)
}

compose.desktop {
    application {
        mainClass = "com.jetbrains.kmpapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg)
            packageName = "com.jetbrains.kmpapp"
            packageVersion = "1.0.0"
        }
    }
}

// Render tiap layar aplikasi menjadi PNG (dipakai untuk gambar di buku).
tasks.register<JavaExec>("screenshots") {
    group = "verification"
    description = "Render layar DompetApp menjadi berkas PNG."
    mainClass.set("com.jetbrains.kmpapp.ScreenshotKt")
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf(rootProject.layout.projectDirectory.dir("screenshots").asFile.absolutePath)
}
