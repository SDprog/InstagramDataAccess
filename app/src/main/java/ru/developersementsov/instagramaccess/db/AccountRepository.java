package ru.developersementsov.instagramaccess.db;

import android.app.Application;
import android.os.AsyncTask;

import ru.developersementsov.instagramaccess.model.Account;

public class AccountRepository {
    private AccountDao mAccountDao;

    public AccountRepository(Application application) {
        AccountDB db = AccountDB.getDatabase(application);
        mAccountDao = db.accountDao();
    }

    public void insert(Account account) {
        new insertAsyncTask(mAccountDao).execute(account);
    }

    private static class insertAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao mAsyncTaskDao;

        insertAsyncTask(AccountDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}