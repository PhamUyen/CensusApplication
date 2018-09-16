package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private static final String TAG = "QuestionDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static QuestionDAO mInstance;

    public static synchronized QuestionDAO getInstance() {
        if (null == mInstance) {
            mInstance = new QuestionDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public QuestionDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<QuestionDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(QuestionDTO cacheEntity) {
        long result = mLiteOrm.insert(cacheEntity);
        return result;
    }

    public int update(QuestionDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(QuestionDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(QuestionDTO.class));
    }

    public List<QuestionDTO> getAllArea() {
        List<QuestionDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(QuestionDTO.class)
        );
        return offlineEntities == null ? new ArrayList<QuestionDTO>() : offlineEntities;
    }

    public List<QuestionDTO> getAllCacheByType(String key) {
        List<QuestionDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(QuestionDTO.class)
                        .whereIn(QuestionDTO.COLUMN_TYPE, key)

        );
        return areaEntities == null ? new ArrayList<QuestionDTO>() : areaEntities;
    }
//    public List<QuestionDTO> getAllCacheByDate(String date) {
//        List<QuestionDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(QuestionDTO.class)
//                        .whereIn(QuestionDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<QuestionDTO>() : areaEntities;
//    }
//    public QuestionDTO getCacheById(String id, String key) {
//        List<QuestionDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(QuestionDTO.class)
//                        .whereIn(QuestionDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(QuestionDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public QuestionDTO getCacheByIdAndDate(String date, String id) {
//        List<QuestionDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(QuestionDTO.class)
//                        .whereIn(QuestionDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(QuestionDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public QuestionDTO findById(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(QuestionDTO.class)
                            .whereEquals(QuestionDTO.ID_CACHE, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(QuestionDTO.class)
                    .where(QuestionDTO.ID_CACHE, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<QuestionDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(QuestionDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<QuestionDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(QuestionDTO.class)
                        .whereIn(QuestionDTO.ID_CACHE, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
