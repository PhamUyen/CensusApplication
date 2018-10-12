package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class FamilyDetailDAO {
    private static final String TAG = "FamilyDetailDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static FamilyDetailDAO mInstance;

    public static synchronized FamilyDetailDAO getInstance() {
        if (null == mInstance) {
            mInstance = new FamilyDetailDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public FamilyDetailDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<FamilyDetailDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(FamilyDetailDTO familyDTO) {
        if(checkIsExistDB(familyDTO.getIDHO())){
            return update(familyDTO);
        }else {
            return  mLiteOrm.insert(familyDTO);
        }
    }

    public int update(FamilyDetailDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(FamilyDetailDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
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

//    public List<FamilyDetailDTO> getAllCacheByType(String key) {
//        List<FamilyDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(FamilyDetailDTO.class)
//                        .whereIn(FamilyDetailDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<FamilyDetailDTO>() : areaEntities;
//    }
//    public List<FamilyDetailDTO> getAllCacheByDate(String date) {
//        List<FamilyDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(FamilyDetailDTO.class)
//                        .whereIn(FamilyDetailDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<FamilyDetailDTO>() : areaEntities;
//    }
//    public FamilyDetailDTO getCacheById(String id, String key) {
//        List<FamilyDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(FamilyDetailDTO.class)
//                        .whereIn(FamilyDetailDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(FamilyDetailDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public FamilyDetailDTO getCacheByIdAndDate(String date, String id) {
//        List<FamilyDetailDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(FamilyDetailDTO.class)
//                        .whereIn(FamilyDetailDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(FamilyDetailDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public FamilyDetailDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(FamilyDetailDTO.class)
                            .whereEquals(FamilyDetailDTO.ID_FAMILY, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
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
