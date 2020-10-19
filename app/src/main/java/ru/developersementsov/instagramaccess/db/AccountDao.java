package ru.developersementsov.instagramaccess.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.developersementsov.instagramaccess.model.Account;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

//    @Query("DELETE FROM account_table")
//    void deleteAll();
//
//    @Query("SELECT * from account_table")
//    LiveData<List<Account>> getAllAccounts();

}
