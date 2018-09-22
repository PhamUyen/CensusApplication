package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class WomanDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static WomanDAO mInstance;

    public static synchronized WomanDAO getInstance() {
        if (null == mInstance) {
            mInstance = new WomanDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public WomanDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<WomanDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(WomanDTO cacheEntity) {
        long result = mLiteOrm.insert(cacheEntity);
        return result;
    }

    public int update(WomanDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(WomanDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(WomanDTO.class));
    }

    public List<WomanDTO> getAllArea() {
        List<WomanDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(WomanDTO.class)
        );
        return offlineEntities == null ? new ArrayList<WomanDTO>() : offlineEntities;
    }

//    public List<WomanDTO> getAllCacheByType(String key) {
//        List<WomanDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(WomanDTO.class)
//                        .whereIn(WomanDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<WomanDTO>() : areaEntities;
//    }
//    public List<WomanDTO> getAllCacheByDate(String date) {
//        List<WomanDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(WomanDTO.class)
//                        .whereIn(WomanDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<WomanDTO>() : areaEntities;
//    }
//    public WomanDTO getCacheById(String id, String key) {
//        List<WomanDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(WomanDTO.class)
//                        .whereIn(WomanDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(WomanDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public WomanDTO getCacheByIdAndDate(String date, String id) {
//        List<WomanDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(WomanDTO.class)
//                        .whereIn(WomanDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(WomanDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public WomanDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(WomanDTO.class)
                            .whereEquals(WomanDTO.ID_HO, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(WomanDTO.class)
                    .where(WomanDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<WomanDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(WomanDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<WomanDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(WomanDTO.class)
                        .whereIn(WomanDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
