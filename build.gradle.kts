plugins {
    id ("java")
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.seleniumhq.selenium:selenium-java:4.43.0")
    implementation("io.github.bonigarcia:webdrivermanager:6.1.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.apache.logging.log4j:log4j-core:2.25.5")
    implementation("org.apache.logging.log4j:log4j-api:2.25.5")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("browser",System.getProperty("browser", "chrome"))
    systemProperty("login",System.getProperty("login", "inurtazin"))
    systemProperty("password",System.getProperty("password", "123456"))
    systemProperty("baseUrl", "https://wishlist.otus.kartushin.su")
}
