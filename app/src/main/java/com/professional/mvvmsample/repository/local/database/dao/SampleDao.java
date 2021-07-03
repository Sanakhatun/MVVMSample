package com.professional.mvvmsample.repository.local.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.professional.mvvmsample.repository.local.database.Sample;

import java.util.List;

@Dao
public interface SampleDao {
    @Insert
    void insert(Sample sample);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Sample sample);

    @Delete
    void delete(Sample sample);

    @Query("SELECT * FROM Sample")
    LiveData<List<Sample>> getRecords();

    @Query("SELECT EXISTS(SELECT * FROM Sample WHERE id = :id)")
    boolean isRowIsExist(int id);
}
