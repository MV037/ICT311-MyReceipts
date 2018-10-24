package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.ReceiptDbSchema;
import com.bignerdranch.android.criminalintent.database.ReceiptBaseHelper;
import com.bignerdranch.android.criminalintent.database.ReceiptCursorWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainLab {

    private static MainLab sMainLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MainLab get(Context context) {
        if (sMainLab == null) {
            sMainLab = new MainLab(context);
        }
        return sMainLab;
    }
    private MainLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ReceiptBaseHelper(mContext)
                .getWritableDatabase();
            }

    public void addCrime(Main c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(ReceiptDbSchema.ReceiptTable.NAME, null, values);
    }

    public List<Main> getCrimes(){
        List<Main> mains = new ArrayList<>();
        ReceiptCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mains.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return mains;

    }


    public Main getCrime(UUID id){
        ReceiptCursorWrapper cursor = queryCrimes(
                ReceiptDbSchema.ReceiptTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Main main) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, main.getPhotoFilename());
    }


    public void updateCrime(Main main) {
        String uuidString = main.getId().toString();
        ContentValues values = getContentValues(main);
        mDatabase.update(ReceiptDbSchema.ReceiptTable.NAME, values,
                ReceiptDbSchema.ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private ReceiptCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ReceiptDbSchema.ReceiptTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ReceiptCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Main main) {
        ContentValues values = new ContentValues();
        values.put(ReceiptDbSchema.ReceiptTable.Cols.UUID, main.getId().toString());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.TITLE, main.getTitle());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.SHOP, main.getShopname());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.COMMENTS, main.getComments());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.DATE, main.getDate().getTime());
        return values;
    }

    public void deleteCrime (Main c) {
        mDatabase.delete(ReceiptDbSchema.ReceiptTable.NAME,
                ReceiptDbSchema.ReceiptTable.Cols.UUID + " = ?",
                new String[]{c.getId().toString()});
    }

}
