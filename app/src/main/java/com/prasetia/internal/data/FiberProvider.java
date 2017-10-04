package com.prasetia.internal.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.prasetia.internal.data.FiberDBHelper.TABLE_PROJECT_FO_NAME;

/**
 * Created by prasetia on 10/4/2017.
 */

public class FiberProvider extends ContentProvider {

    public static final int PROJECT_FO = 100;

    FiberDBHelper fiberDBHelper;
    UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        fiberDBHelper = new FiberDBHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.prasetia.internal", "project_fo", PROJECT_FO);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionargs,
                        @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case PROJECT_FO:
                cursor = fiberDBHelper.getReadableDatabase().query(
                        TABLE_PROJECT_FO_NAME,
                        projection,
                        selection,
                        selectionargs,
                        null, null,
                        sortOrder
                );
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case PROJECT_FO:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "com.prasetia.internal/project_fo";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)){
            case PROJECT_FO:
                long id = fiberDBHelper.getWritableDatabase().insert(
                        FiberDBHelper.TABLE_PROJECT_FO_NAME,
                        null,
                        contentValues
                );
                return Uri.parse("content://com.prasetia.internal/project_fo/"+id);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case PROJECT_FO:
                int row_number = fiberDBHelper.getWritableDatabase().delete(
                        FiberDBHelper.TABLE_PROJECT_FO_NAME,
                        selection,
                        selectionArgs
                );
                return row_number;
        }
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        switch (uriMatcher.match(uri)){
            case PROJECT_FO:
                int returnCount = 0;

                SQLiteDatabase sqLiteDatabase = fiberDBHelper.getWritableDatabase();
                sqLiteDatabase.beginTransaction();
                try {
                    for (ContentValues cv : values){
                        sqLiteDatabase.insert(
                                FiberDBHelper.TABLE_PROJECT_FO_NAME,
                                null,
                                cv
                        );
                        returnCount++;
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                }catch (Exception e){
                    returnCount = 0;
                }finally {
                    sqLiteDatabase.endTransaction();
                }
                return returnCount;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        switch(uriMatcher.match(uri)){
            case PROJECT_FO:
                int row_number = fiberDBHelper.getWritableDatabase().update(
                        FiberDBHelper.TABLE_PROJECT_FO_NAME,
                        contentValues,
                        selection,
                        selectionArgs
                );
                return row_number;
        }
        return 0;
    }
}
