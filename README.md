# MarqueeTextView

Use MarqueeTextView to display rtl (or ltr) text with proper movement direction.

Based on open source lib: https://github.com/shachar-oren/android-rtlmarqueeview

## Preview

<img src="/app/src/main/res/drawable/preview_light.gif" alt="preview_light" width="50%"/>

## Integration

Add the dependency:

```groovy
dependencies {
    implementation 'io.github.chayanforyou:marquee:1.0.0'
}
```
## Usage

Example usage in XML:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="16dp"
    tools:context=".MainActivity">

    <io.github.chayanforyou.marquee.MarqueeTextView
        android:id="@+id/rmv_marquee"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:marqueeEndWaitTicks="30"
        app:marqueeFadeToColor="?android:windowBackground"
        app:marqueeFontFamily="@font/ubuntu_regular"
        app:marqueeIsLooping="true"
        app:marqueeLoops="0"
        app:marqueeStartWaitTicks="50"
        app:marqueeText="@string/sample_text"
        app:marqueeTextColor="?android:colorAccent"
        app:marqueeTextSize="18sp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

Layout direction is checked internally. To manually update it, call `updateRtl()`.

## Public methods

```java
void setText(String text)
String getText()

void setTextColor(int color)
int getTextColor()

void setTextSize(float size)
float getTextSize()

void setTypeface()
Typeface getTypeface()

void setFadeToColor(int color)
int getFadeToColor()

void setLooping(boolean looping)
boolean getLooping()

void setLoops(int loops)
int getLoops()

void setStartWaitTicks(int ticks)
int getStartWaitTicks()

void setEndWaitTicks(int ticks)
int getEndWaitTicks()
```

## License

```text
Copyright 2024 Chayan Mistry

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
