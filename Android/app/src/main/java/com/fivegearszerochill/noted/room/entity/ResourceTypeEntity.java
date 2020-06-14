package com.fivegearszerochill.noted.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "resource_type")
public class ResourceTypeEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type_id")
    private int typeId;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public ResourceTypeEntity(String description, boolean isActive) {
        this.description = description;
        this.isActive = isActive;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }
}
