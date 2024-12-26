plugins {
    id("java")
}

group = "ru.enoughLev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.11.0");
    implementation("org.projectlombok:lombok:1.18.36");
    compileOnly("org.projectlombok:lombok:1.18.36");
    annotationProcessor("org.projectlombok:lombok:1.18.36");
    testCompileOnly("org.projectlombok:lombok:1.18.36");
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36");
}


tasks.test {
    useJUnitPlatform()
}