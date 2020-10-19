package ru.developersementsov.instagramaccess.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import ru.developersementsov.instagramaccess.model.Account;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);
}
