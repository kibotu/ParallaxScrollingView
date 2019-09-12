[![Donation](https://img.shields.io/badge/buy%20me%20a%20coffee-brightgreen.svg)](https://www.paypal.me/janrabe/5) [![About Jan Rabe](https://img.shields.io/badge/about-me-green.svg)](https://about.me/janrabe)
# ParallaxScrollingView [![](https://jitpack.io/v/kibotu/ParallaxScrollingView.svg)](https://jitpack.io/#kibotu/ParallaxScrollingView) [![](https://jitpack.io/v/kibotu/ParallaxScrollingView/month.svg)](https://jitpack.io/#kibotu/ParallaxScrollingView) [![Hits-of-Code](https://hitsofcode.com/github/kibotu/ParallaxScrollingView)](https://hitsofcode.com/view/github/kibotu/ParallaxScrollingView) [![Javadoc](https://img.shields.io/badge/javadoc-SNAPSHOT-green.svg)](https://jitpack.io/com/github/kibotu/ParallaxScrollingView/master-SNAPSHOT/javadoc/index.html) [![Build Status](https://travis-ci.org/kibotu/ParallaxScrollingView.svg)](https://travis-ci.org/kibotu/ParallaxScrollingView)  [![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15) [![Gradle Version](https://img.shields.io/badge/gradle-5.6.1-green.svg)](https://docs.gradle.org/current/release-notes)  [![Kotlin](https://img.shields.io/badge/kotlin-1.3.50-green.svg)](https://kotlinlang.org/) [![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/kibotu/ParallaxScrollingView/master/LICENSE) [![androidx](https://img.shields.io/badge/androidx-brightgreen.svg)](https://developer.android.com/topic/libraries/support-library/refactor)

Parallax Scrolling View.

- automatic scrolling with different speeds
- minimal integration
- gpu accelerated
- supports ViewPager2
- argb interpolated gradient on viewpager scrolling
- updates statusBar color on scroll

[![Screenshot](sample_big.gif)](sample_big.gif)

### How to use

1 add ParallaxScrollingView to your layout

    <net.kibotu.parallaxscrollingview.ParallaxScrollingView
        android:id="@+id/wave1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:speed="@dimen/wave1_speed"
        app:src="@drawable/ic_wave" />

2 (Optional) add ParallaxScrollingViewOnPageScrollListener to ViewPager2

    viewPager.registerOnPageChangeCallback(ParallaxScrollingViewOnPageScrollListener(listOf(wave1, wave2, wave3, wave4, wave5, wave6), 2f))

3(Optional) add OffsetOnPageScrollListener to ViewPager2

    viewPager.registerOnPageChangeCallback(OffsetOnPageScrollListener(this, root, items.indices.map { backgrounds[it] }, true))

### How to install

	repositories {
	    maven {
	        url "https://jitpack.io"
	    }
	}

	dependencies {
        implementation 'com.github.kibotu:ParallaxScrollingView:-SNAPSHOT'
    }

### License
<pre>
Copyright 2019 Jan Rabe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
