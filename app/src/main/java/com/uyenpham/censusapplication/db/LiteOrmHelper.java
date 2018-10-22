package com.uyenpham.censusapplication.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.SQLiteHelper;
import com.uyenpham.censusapplication.App;

public class LiteOrmHelper {
    private static final String DB_NAME = "census-app";
    private static final int DB_VERSION = 2;

    private static volatile LiteOrm sInstance;

    private LiteOrmHelper() {
        // Avoid direct instantiate
    }

    public static LiteOrm getInstance() {
        if (sInstance == null) {
            synchronized (LiteOrmHelper.class) {
                if (sInstance == null) {
                    sInstance = LiteOrm.newCascadeInstance(new DataBaseConfig(App.getInstance(), DB_NAME, true, DB_VERSION, new SQLiteHelper.OnUpdateListener() {
                        @Override
                        public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                            Log.e("LiteOrmHelper", "onUpdate :: version ==>" + sqLiteDatabase.getVersion() + " | i == " + i + " | i1 == " + i1);
                        }
                    }));
                }
            }
        }
        return sInstance;
    }
}
