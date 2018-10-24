package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.locality.LocalityDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LocalityDAO {
    private static final String TAG = "LocalityDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static LocalityDAO mInstance;
//    private DBHelper  dbHelper = new DBHelper(App.getInstance());
    private Dao<LocalityDTO, ?> dao;
//
//    {
//        try {
//            dao = dbHelper.getDao(LocalityDTO.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static synchronized LocalityDAO getInstance() {
        if (null == mInstance) {
            mInstance = new LocalityDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    private LocalityDAO(DBHelper  dbHelper) {
        try {
            dao = dbHelper.getDao(LocalityDTO.class);
        } catch (SQLException e) {
           Logger.e(e);
        }
    }

//    public long insertAllOffline(List<LocalityDTO> offlineEntities) {
//        if (null == offlineEntities) return -1;
//
//        deleteAllOffline();
//
//        long result = mLiteOrm.save(offlineEntities);
//
//        return result;
//    }

    public void insert(LocalityDTO familyDTO) {
            try {
                 dao.createOrUpdate(familyDTO);
            } catch (SQLException e) {
             Logger.e(e);
            }
    }
//    public int update(LocalityDTO cacheEntity) {
//        int result = mLiteOrm.update(cacheEntity);
//        return result;
//    }

    public void delete(LocalityDTO cacheEntity) {
//        int result = mLiteOrm.delete(cacheEntity);
//        return result;
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(LocalityDTO.class));
    }

    public List<LocalityDTO> getAllArea() {
        List<LocalityDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(LocalityDTO.class)
        );
        return offlineEntities == null ? new ArrayList<LocalityDTO>() : offlineEntities;
    }


    public LocalityDTO findById(String id) {
        try {
            return dao.queryForEq(LocalityDTO.ID_LOCALITY,id).get(0);
//            return mLiteOrm.query(
//                    new QueryBuilder<>(LocalityDTO.class)
//                            .whereEquals(LocalityDTO.ID_LOCALITY, id)
//            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public List<LocalityDTO> findByUserId(String user) {
        try {
//            return mLiteOrm.query(
//                    new QueryBuilder<>(LocalityDTO.class)
//                            .whereEquals(LocalityDTO.ID_USER, user)
//            );
            return dao.queryForEq(LocalityDTO.ID_USER,user);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public List<LocalityDTO> findByUserAndLocal(String user, String iddb) {
        com.j256.ormlite.stmt.QueryBuilder<LocalityDTO, ?> queryBuilder = dao.queryBuilder();
        try {
            queryBuilder.where().eq(LocalityDTO.ID_USER, user).and().eq(LocalityDTO.ID_LOCALITY, iddb);
            return queryBuilder.query();
        } catch (SQLException e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }

    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(LocalityDTO.class)
                    .where(LocalityDTO.ID_LOCALITY, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<LocalityDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(LocalityDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<LocalityDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(LocalityDTO.class)
                        .whereIn(LocalityDTO.ID_LOCALITY, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
