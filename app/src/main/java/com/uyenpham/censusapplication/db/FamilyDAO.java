package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;
    private Dao<FamilyDTO, ?> dao;

    private static FamilyDAO mInstance;

    public static synchronized FamilyDAO getInstance() {
        if (null == mInstance) {
            mInstance = new FamilyDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    private FamilyDAO(DBHelper  dbHelper) {
        try {
            dao = dbHelper.getDao(FamilyDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public long insertAllOffline(List<FamilyDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public void insert(FamilyDTO familyDTO) {
        try {
            dao.createOrUpdate(familyDTO);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(FamilyDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(FamilyDTO.class));
    }

    public List<FamilyDTO> getAllArea() {
        List<FamilyDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(FamilyDTO.class)
        );
        return offlineEntities == null ? new ArrayList<FamilyDTO>() : offlineEntities;
    }


    public FamilyDTO findById(String id) {
        try {
            return dao.queryForEq(FamilyDTO.ID_FAMILY,id).get(0);
        } catch (SQLException e) {
            Logger.e(e);
            return null;
        }
    }
    public List<FamilyDTO> findByUserId(String user) {
        try {
            return dao.queryForEq(FamilyDTO.ID_INVESTIGATE,user);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public List<FamilyDTO> findByUserAndLocal(String user, String iddb) {
            com.j256.ormlite.stmt.QueryBuilder<FamilyDTO, ?> queryBuilder = dao.queryBuilder();
            try {
                queryBuilder.where().eq(FamilyDTO.ID_INVESTIGATE, user).and().eq(FamilyDTO.ID_LOCALITY, iddb);
                return queryBuilder.query();
            } catch (SQLException e) {
                Logger.e(TAG, e.getMessage(), e);
                return null;
            }

    }
    public List<FamilyDTO> findNewFamilyByUser(String user) {
        com.j256.ormlite.stmt.QueryBuilder<FamilyDTO, ?> queryBuilder = dao.queryBuilder();
        try {
            queryBuilder.where().eq(FamilyDTO.ID_INVESTIGATE, user).and().eq(FamilyDTO.IS_NEW, true).and().eq(FamilyDTO.ID_CREATE,true);
            return queryBuilder.query();
        } catch (SQLException e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }


    public void deleteByKey(List<FamilyDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(FamilyDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<FamilyDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(FamilyDTO.class)
                        .whereIn(FamilyDTO.ID_FAMILY, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
