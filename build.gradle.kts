plugins {
	kotlin("jvm")
	application
}

group = "com.romangraef"
version = "1.0-SNAPSHOT"

repositories {
	maven {
		name = "Mojang Maven"
		url = uri("https://libraries.minecraft.net")
	}
	jcenter()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	implementation("com.mojang", "brigadier", "_")

	implementation("net.dv8tion", "JDA", "_")

	runtimeOnly("org.slf4j", "slf4j-simple", "_")
}

application {
	mainClassName = "com.romangraef.jdabrigadier.StartKt"
}