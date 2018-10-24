package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyDetailDAO {
    private static final String TAG = "FamilyDetailDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static FamilyDetailDAO mInstance;
    private Dao<FamilyDetailDTO, ?> dao;

    public static synchronized FamilyDetailDAO getInstance() {
        if (null == mInstance) {
            mInstance = new FamilyDetailDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    public FamilyDetailDAO(DBHelper helper) {
        try {
            dao = helper.getDao(FamilyDetailDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public long insertAllOffline(List<FamilyDetailDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public void insert(FamilyDetailDTO familyDTO) {
        try {
            dao.createOrUpdate(familyDTO);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(FamilyDetailDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(FamilyDetailDTO.class));
    }

    public List<FamilyDetailDTO> getAllArea() {
        List<FamilyDetailDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(FamilyDetailDTO.class)
        );
        return offlineEntities == null ? new ArrayList<FamilyDetailDTO>() : offlineEntities;
    }


    public FamilyDetailDTO findById(String id) {
        try {
            return (dao.queryForEq(FamilyDetailDTO.ID_FAMILY, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(FamilyDetailDTO.class)
                    .where(FamilyDetailDTO.ID_FAMILY, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<FamilyDetailDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(FamilyDetailDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<FamilyDetailDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(FamilyDetailDTO.class)
                        .whereIn(FamilyDetailDTO.ID_FAMILY, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
