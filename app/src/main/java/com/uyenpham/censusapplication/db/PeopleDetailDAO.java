package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleDetailDAO {
    private static final String TAG = "PeopleDetailDao";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static PeopleDetailDAO mInstance;
    private Dao<PeopleDetailDTO,?> dao;

    public static synchronized PeopleDetailDAO getInstance() {
        if (null == mInstance) {
            mInstance = new PeopleDetailDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    private PeopleDetailDAO(DBHelper helper) {
        try {
            dao = helper.getDao(PeopleDetailDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public void insert(PeopleDetailDTO cacheEntity) {
        try {
            dao.createOrUpdate(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(PeopleDetailDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public List<PeopleDetailDTO> findById(String id) {
        try {
            return dao.queryForEq(PeopleDetailDTO.ID_HO, id);
        } catch (SQLException e) {
            Logger.e(e);
            return new ArrayList<>();
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(PeopleDetailDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<PeopleDetailDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(PeopleDetailDTO.class)
                        .whereIn(PeopleDetailDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
