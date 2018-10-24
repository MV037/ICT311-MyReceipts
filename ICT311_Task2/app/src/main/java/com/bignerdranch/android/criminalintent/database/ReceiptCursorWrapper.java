package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Main;

import java.util.Date;
import java.util.UUID;

public class ReceiptCursorWrapper extends CursorWrapper {
    public ReceiptCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Main getCrime() {
        String uuidString = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.UUID));
        String title = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.TITLE));
        String shop = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.SHOP));
        String comments = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.COMMENTS));
        long date = getLong(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.DATE));

        Main main = new Main(UUID.fromString(uuidString));
        main.setTitle(title);
        main.setShopname(shop);
        main.setComments(comments);
        main.setDate(new Date(date));


        return main;
    }

}
