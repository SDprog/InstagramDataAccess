package ru.developersementsov.instagramaccess.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "accountName")
    private String accountName;

    public Account(@NonNull String accountName) {
        this.accountName = accountName;
    }

    @NonNull
    public String getAccountName() {
        return this.accountName;
    }
}
