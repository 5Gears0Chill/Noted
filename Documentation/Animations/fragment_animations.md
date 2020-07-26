

# Fragment Animations

In order to allow for the modal (Need to place a reference to modals) to slide up from the bottom of the screen, two xml files were used namely:

1.  `slide_in_up.xml` 
2. `slide_out_up.xml`

Using the `SupportFragmentManager()` it is possible to set custom animations for the entrance and exit of a fragment. 

The following demonstrates the structure of the aforementioned `xml` files.

`slide_in_up.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="@android:integer/config_longAnimTime"
        android:fromYDelta="100%p"
        android:toYDelta="0%p" />
</set>
```

`slide_out_up.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="@android:integer/config_longAnimTime"
        android:fromYDelta="0%p"
        android:toYDelta="-100%p"
        />
</set>
```

For the direction = up (sliding up)

```java
getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
                    .add(R.id.fragment_placeholder, UnsplashFragment.newInstance(), UNSPLASH_FRAGMENT_TAG)
                    .commit();
```



## Other Directions For Reference

For the opposite effect (sliding down from page) use the following code.

`slide_in_down.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="@android:integer/config_longAnimTime"
        android:fromYDelta="0%p"
        android:toYDelta="100%p" />
</set>
```

`slide_out_down.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="@android:integer/config_longAnimTime"
        android:fromYDelta="-100%"
        android:toYDelta="0"
        />
</set>
```

For Direction = down (slide down from page)

```java
  getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_out_down, R.anim.slide_in_down)
                   	.add(R.id.fragment_placeholder, UnsplashFragment.newInstance(), UNSPLASH_FRAGMENT_TAG)
                    .commit();
```

***



| Created On:      | 26 July 2020           |
| ---------------- | ---------------------- |
| **Author:**      | **FiveGearsZeroChill** |
| **Last Updated** | **26 July 2020**       |