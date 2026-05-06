plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.21.0")

    // JUnit 5
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")

    // WebDriverManager
    implementation("io.github.bonigarcia:webdrivermanager:5.8.0")

    // SLF4J logger
    implementation("org.slf4j:slf4j-simple:2.0.13")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.test {
    useJUnitPlatform()
}