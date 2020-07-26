# Shake Animations For Android Cards

In order to allow for a card to animate when being held and introduce a range of options we introduce shake mechanics into the android application.

Two files were added namely: 

1. `shake.xml`
2. `shake_minor.xml` (used for larger cards with smaller degrees of rotation)

The xml files are shown below:

`shake.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="100"
    android:fromDegrees="-2"
    android:pivotX="50%"
    android:pivotY="50%"
    android:repeatCount="infinite"
    android:repeatMode="reverse"
    android:toDegrees="2" />
```

`shake_minor.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="100"
    android:fromDegrees="-1"
    android:pivotX="50%"
    android:pivotY="50%"
    android:repeatCount="infinite"
    android:repeatMode="reverse"
    android:toDegrees="1" />
```

The files are added to cards as follows:

```java
 Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
 view.startAnimation(animation);
```

where the `view` is passed through an interface (place link to android hooks for recycler views)

To turn off the animation, simply call:

```java
 view.clearAnimation();
```

on the `view` holding the animation.

***



| Created On:      | 26 July 2020           |
| ---------------- | ---------------------- |
| **Author:**      | **FiveGearsZeroChill** |
| **Last Updated** | **26 July 2020**       |

