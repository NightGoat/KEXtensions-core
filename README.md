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

```
MIT License

Copyright (c) 2022 NightGoat

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
