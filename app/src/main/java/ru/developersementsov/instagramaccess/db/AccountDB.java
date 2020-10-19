package ru.developersementsov.instagramaccess.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            //.fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return dataBase;
    }

//    private static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback(){
//
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                    new PopulateDbAsync(dataBase).execute();
//                }
//            };

//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final AccountDao mDao;
//        String[] words = {"dolphin", "crocodile", "cobra"};
//
//        PopulateDbAsync(AccountDB db) {
//            mDao = db.accountDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate the database
//            // when it is first created
//            mDao.deleteAll();
//
////            for (int i = 0; i <= words.length - 1; i++) {
////                Account account = new Account(words[i]);
////                mDao.insert(account);
////            }
//            return null;
//        }
//    }
}
