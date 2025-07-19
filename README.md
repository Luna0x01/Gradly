**⚠️ No Longer Maintained**

---


<center>
<img src="assets/logo.png" style="border-radius: 10%; margin-top: 40px; margin-bottom: -40px" />
<h1 style="margin-bottom: -20px">Gradly</h1>
<h3>A simple gradient generator for Java and Kotlin</h3>
</center>

---

## Features
- Noise
- Random gradient positioning
- Output to any format you want (i.e. png, bmp, webp)

## Setting up
Add https://github.com/JetBrains/skiko to your build.gradle as it is a requirement and it should be in the classpath during runtime!

Add our maven repository:

https://maven.vexor.dev/releases

Then, add the dependency:

`implementation("dev.lunasa:gradly:1.0.0")`

You're all setup!

## Example

Kotlin Example:

```kotlin
val colors = listOf(
    Color(100, 100, 255).rgb,
    Color(30, 70, 110).rgb,
    Color(90, 64, 200).rgb,
    Color(30, 23, 150).rgb
)

val image = Gradly.createGradientWithNoise(
    90f,
    90f,
    colors,
    noiseLevel = 15
)
```

Output: (Look at our logo!)

---

Java example:
```java
List<Integer> colors = List.of(
        new Color(100, 100, 255).getRGB(),
        new Color(30, 70, 110).getRGB(),
        new Colr(90, 64, 200).getRGB(),
        new Color(30, 23, 150).getRGB()
);

var image = Gradly.createGradientWithNoise(
        90f,
        90f,
        colors,
        ImageFormat.PNG,
        15
);
```

Output: (Look at our logo!)

## Contributions

Contributions are always welcome. Fork this repository, make your changes, and create a PR!

## [License](LICENSE.md)

This project is licensed under LGPL 3.0.