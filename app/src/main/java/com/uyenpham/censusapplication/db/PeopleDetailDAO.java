package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class PeopleDetailDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static PeopleDetailDAO mInstance;

    public static synchronized PeopleDetailDAO getInstance() {
        if (null == mInstance) {
            mInstance = new PeopleDetailDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public PeopleDetailDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<PeopleDetailDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(PeopleDetailDTO cacheEntity) {
        long result = mLiteOrm.insert(cacheEntity);
        return result;
    }

    public int update(PeopleDetailDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(PeopleDetailDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(PeopleDetailDTO.class));
    }

    public List<PeopleDetailDTO> getAllArea() {
        List<PeopleDetailDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(PeopleDetailDTO.class)
        );
        return offlineEntities == null ? new ArrayList<PeopleDetailDTO>() : offlineEntities;
    }

//    public List<PeopleDetailDTO> getAllCacheByType(String key) {
//        List<PeopleDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDetailDTO.class)
//                        .whereIn(PeopleDetailDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<PeopleDetailDTO>() : areaEntities;
//    }
//    public List<PeopleDetailDTO> getAllCacheByDate(String date) {
//        List<PeopleDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDetailDTO.class)
//                        .whereIn(PeopleDetailDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<PeopleDetailDTO>() : areaEntities;
//    }
//    public PeopleDetailDTO getCacheById(String id, String key) {
//        List<PeopleDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDetailDTO.class)
//                        .whereIn(PeopleDetailDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(PeopleDetailDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public PeopleDetailDTO getCacheByIdAndDate(String date, String id) {
//        List<PeopleDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDetailDTO.class)
//                        .whereIn(PeopleDetailDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(PeopleDetailDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public PeopleDetailDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(PeopleDetailDTO.class)
                            .whereEquals(PeopleDetailDTO.ID_HO, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(PeopleDetailDTO.class)
                    .where(PeopleDetailDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<PeopleDetailDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
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
