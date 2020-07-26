# Staggered Grid layout for Asymmetrical  Cards In Android

This is an overview of how to implement the staggered grid layout for android.

It is assumed that knowledge on creating standard recycler views are known the following components are already created:

1. `RecyclerViewAdapter`
2. `RecyclerViewHolder`
3. `CardView` for custom components.

On declaring the recycler view with the correct binding. It should be noted that the only major change is the `LayoutManager` :

```java
 StaggeredGridLayoutManager _sGridLayoutManager; = new StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(_sGridLayoutManager);
```

Where the parameters for the `StaggeredGridLayoutManager` are as follows: 

- Number of columns.
- Orientation of scroll i.e. `VERTICAL` or `HORIZONTAL`

***


| Created On:      | 26 July 2020           |
| ---------------- | ---------------------- |
| **Author:**      | **FiveGearsZeroChill** |
| **Last Updated** | **26 July 2020**       |

