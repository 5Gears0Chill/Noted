# Room Database Implementation.

Room is a ORM for android that maps objects to a RD (Relational Database). 

In order to use Room in a project. The `build.gradle` file is to be modified with the following dependencies:

```groovy
   //Room Dependencies
    def room_version = "2.2.5"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    debugImplementation 'com.amitshekhar.android:debug-db:1.0.6'
```

***


| Created On:      | 26 July 2020           |
| ---------------- | ---------------------- |
| **Author:**      | **FiveGearsZeroChill** |
| **Last Updated** | **26 July 2020**       |