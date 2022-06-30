# KEXtensions core
Kotlin extensions library.

## Examples:
Using this library does not require knowledge or skill, this extensions will work as part of Kotlin language.

## Logging
Some extensions do require logging, as they catching exceptions or catching null values from some places. Default Logger is just println() fun.
If you want you can have your custom logger, you need to use ILogger interface from library and set your logger to Kex:
```
object MyCustomLogger: ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        //Put your logging code here
    }
}

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Kex.setCustomLogger(MyCustomLogger)
    }
}
```
Or you can just turn it off: 
```
Kex.turnOffLogger()
```

## How to add it:
* Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
  buildscript {
    repositories {
        mavenCentral()
    }
```
* Step 2. Add the dependency
```
dependencies {
	        implementation("io.github.nightgoat:Kextensions-core:1.0")
	}
```
kmm:
```
sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.github.nightgoat:Kextensions-core:1.0")
            }
        }
```
