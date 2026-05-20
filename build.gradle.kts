
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

// Allows runClient to work despite some jank in Galacticraft's MicdoodleTransformer
tasks.withType<JavaExec>().configureEach {
    jvmArgs("-noverify")
}
