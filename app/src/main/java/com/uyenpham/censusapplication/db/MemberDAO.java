package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static MemberDAO mInstance;

    public static synchronized MemberDAO getInstance() {
        if (null == mInstance) {
            mInstance = new MemberDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public MemberDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<MemberDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(MemberDTO cacheEntity) {
        long result = mLiteOrm.insert(cacheEntity);
        return result;
    }

    public int update(MemberDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(MemberDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(MemberDTO.class));
    }

    public List<MemberDTO> getAllArea() {
        List<MemberDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(MemberDTO.class)
        );
        return offlineEntities == null ? new ArrayList<MemberDTO>() : offlineEntities;
    }

//    public List<MemberDTO> getAllCacheByType(String key) {
//        List<MemberDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(MemberDTO.class)
//                        .whereIn(MemberDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<MemberDTO>() : areaEntities;
//    }
//    public List<MemberDTO> getAllCacheByDate(String date) {
//        List<MemberDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(MemberDTO.class)
//                        .whereIn(MemberDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<MemberDTO>() : areaEntities;
//    }
//    public MemberDTO getCacheById(String id, String key) {
//        List<MemberDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(MemberDTO.class)
//                        .whereIn(MemberDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(MemberDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public MemberDTO getCacheByIdAndDate(String date, String id) {
//        List<MemberDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(MemberDTO.class)
//                        .whereIn(MemberDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(MemberDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public MemberDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(MemberDTO.class)
                            .whereEquals(MemberDTO.ID_HO, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(MemberDTO.class)
                    .where(MemberDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<MemberDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(MemberDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<MemberDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(MemberDTO.class)
                        .whereIn(MemberDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
