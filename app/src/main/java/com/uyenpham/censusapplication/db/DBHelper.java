package com.uyenpham.censusapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.locality.LocalityDTO;

import org.apache.poi.ss.formula.functions.T;

import java.sql.SQLException;
import java.util.List;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    // Fields

    public static final String DB_NAME = "CensusApplication.db";
    private static final int DB_VERSION = 1;

    // Public methods

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {

           // Create Table with given table name with columnName
            TableUtils.createTable(cs, LocalityDTO.class);
            TableUtils.createTable(cs, FamilyDTO.class);
            TableUtils.createTable(cs, DeadDTO.class);
            TableUtils.createTable(cs, FamilyDetailDTO.class);
            TableUtils.createTable(cs, HouseDTO.class);
            TableUtils.createTable(cs, MemberDTO.class);
            TableUtils.createTable(cs, PeopleDTO.class);
            TableUtils.createTable(cs, FamilyDetailDTO.class);
            TableUtils.createTable(cs, WomanDTO.class);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }
    public List getAll(Class clazz) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForAll();
    }

    public  T getById(Class clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.queryForId(aId);
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    public  int deleteById(Class clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.deleteById(aId);
    }
}
