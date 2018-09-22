package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class DeadDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static DeadDAO mInstance;

    public static synchronized DeadDAO getInstance() {
        if (null == mInstance) {
            mInstance = new DeadDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public DeadDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<DeadDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(DeadDTO cacheEntity) {
        long result = mLiteOrm.insert(cacheEntity);
        return result;
    }

    public int update(DeadDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(DeadDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
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

//    public List<DeadDTO> getAllCacheByType(String key) {
//        List<DeadDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(DeadDTO.class)
//                        .whereIn(DeadDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<DeadDTO>() : areaEntities;
//    }
//    public List<DeadDTO> getAllCacheByDate(String date) {
//        List<DeadDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(DeadDTO.class)
//                        .whereIn(DeadDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<DeadDTO>() : areaEntities;
//    }
//    public DeadDTO getCacheById(String id, String key) {
//        List<DeadDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(DeadDTO.class)
//                        .whereIn(DeadDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(DeadDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public DeadDTO getCacheByIdAndDate(String date, String id) {
//        List<DeadDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(DeadDTO.class)
//                        .whereIn(DeadDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(DeadDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public DeadDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(DeadDTO.class)
                            .whereEquals(DeadDTO.ID_HO, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
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
