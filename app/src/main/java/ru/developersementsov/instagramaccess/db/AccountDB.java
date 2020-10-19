package ru.developersementsov.instagramaccess.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.developersementsov.instagramaccess.model.Account;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountDB extends RoomDatabase {
    public abstract AccountDao accountDao();

    private static AccountDB dataBase;

    static AccountDB getDatabase(final Context context) {
        if (dataBase == null) {
            synchronized (AccountDB.class) {
                if (dataBase == null) {
                    dataBase = Room.databaseBuilder(context.getApplicationContext(),
                            AccountDB.class, "account_database")
                            .build();
                }
            }
        }
        return dataBase;
    }
}
