plugins {
    kotlin("jvm") version "2.0.20"
    `maven-publish`
}

group = "dev.lunasa"
version = "1.0.0"

repositories {
    mavenCentral()
}

val osName = System.getProperty("os.name")
val targetOs = when {
    osName == "Mac OS X" -> "macos"
    osName.startsWith("Win") -> "windows"
    osName.startsWith("Linux") -> "linux"
    else -> error("Unsupported OS: $osName")
}

val osArch = System.getProperty("os.arch")
val targetArch = when (osArch) {
    "x86_64", "amd64" -> "x64"
    "aarch64" -> "arm64"
    else -> error("Unsupported arch: $osArch")
}
val target = "${targetOs}-${targetArch}"

dependencies {
    api("org.jetbrains.skiko:skiko-awt-runtime-$target:0.8.9")
}

publishing {
    repositories {
        maven {
            name = "Vexor"
            url = uri("https://maven.vexor.dev/releases")
            credentials(PasswordCredentials::class) {
                username = properties["user"].toString()
                password = properties["passkey"].toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.toString()
            version = project.version.toString()
            from(components["java"])
        }
    }
}
kotlin {
    jvmToolchain(17)
}