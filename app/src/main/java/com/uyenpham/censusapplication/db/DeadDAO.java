package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeadDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;
    private Dao<DeadDTO, ?> dao;

    private static DeadDAO mInstance;

    public static synchronized DeadDAO getInstance() {
        if (null == mInstance) {
            mInstance = new DeadDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    public DeadDAO(DBHelper helper) {
        try {
            dao = helper.getDao(DeadDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public long insertAllOffline(List<DeadDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public void insert(DeadDTO deadDTO) {
        try {
            dao.createOrUpdate(deadDTO);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(DeadDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(DeadDTO.class));
    }

    public List<DeadDTO> getAllArea() {
        List<DeadDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(DeadDTO.class)
        );
        return offlineEntities == null ? new ArrayList<DeadDTO>() : offlineEntities;
    }


    public List<DeadDTO> findById(String id) {
        try {
            return dao.queryForEq(DeadDTO.ID_HO, id);
        } catch (Exception e) {
            Logger.e(e);
            return  new ArrayList<>();
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(DeadDTO.class)
                    .where(DeadDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<DeadDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(DeadDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<DeadDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(DeadDTO.class)
                        .whereIn(DeadDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
