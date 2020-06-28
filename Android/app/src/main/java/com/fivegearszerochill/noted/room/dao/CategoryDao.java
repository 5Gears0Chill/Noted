package com.fivegearszerochill.noted.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.fivegearszerochill.noted.room.entity.NoteCategoryEntity;

@Dao
public abstract class CategoryDao {

    @Transaction
    @Query(
            "SELECT * FROM note_category"
    )
    public abstract NoteCategoryEntity getAllCategories();

    @Insert
    public abstract long createCategory(NoteCategoryEntity category);


}
