package com.prasetia.internal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.prasetia.internal.config.config.DB_NAME;
import static com.prasetia.internal.config.config.VERSION;

/**
 * Created by prasetia on 10/4/2017.
 */

public class FiberDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_PROJECT_FO_NAME = "project_fo";
    public static final String COLUMN_ID_PROJECT_FO = "id";
    public static final String COLUMN_CUSTOMER_NAME_PROJECT_FO = "customer_name";
    public static final String COLUMN_CUSTOMER_ID_PROJECT_FO = "customer_id";

    public FiberDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_PROJECT_FO_NAME + " (" +
                COLUMN_ID_PROJECT_FO + " int, " +
                COLUMN_CUSTOMER_NAME_PROJECT_FO + " text, " +
                COLUMN_CUSTOMER_ID_PROJECT_FO + " text, " +
                "unique (" + COLUMN_ID_PROJECT_FO + ") on conflict replace" +
                ");");
    }

    private void reCreateDB(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_PROJECT_FO_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        reCreateDB(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        reCreateDB(db);
    }
}
