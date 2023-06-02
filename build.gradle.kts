plugins {
	java
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.test"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	//new repository
	gradlePluginPortal()
	//
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator") //metrics
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.projectlombok:lombok:1.18.26")
	implementation("io.micrometer:micrometer-registry-prometheus:1.11.0") //metrics
    runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	//Lombok
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	testCompileOnly ("org.projectlombok:lombok:1.18.26")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.26")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//prevent thin-jar generation
tasks.jar {
	enabled = false
}

//rename resulting fat-Jar
tasks.bootJar {
	archiveFileName.set("myapp.jar")
}
