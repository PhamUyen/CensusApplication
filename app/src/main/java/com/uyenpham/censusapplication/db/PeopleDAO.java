package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class PeopleDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static PeopleDAO mInstance;

    public static synchronized PeopleDAO getInstance() {
        if (null == mInstance) {
            mInstance = new PeopleDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public PeopleDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<PeopleDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(PeopleDTO peopleDTO) {
        if(checkIsExistDB(peopleDTO.getID())){
            return update(peopleDTO);
        }else {
            return mLiteOrm.insert(peopleDTO);
        }
    }

    public int update(PeopleDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(PeopleDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(PeopleDTO.class));
    }

    public List<PeopleDTO> getAllArea() {
        List<PeopleDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(PeopleDTO.class)
        );
        return offlineEntities == null ? new ArrayList<PeopleDTO>() : offlineEntities;
    }

//    public List<PeopleDTO> getAllCacheByType(String key) {
//        List<PeopleDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDTO.class)
//                        .whereIn(PeopleDTO.COLUMN_TYPE, key)
//
//        );
//        return areaEntities == null ? new ArrayList<PeopleDTO>() : areaEntities;
//    }
//    public List<PeopleDTO> getAllCacheByDate(String date) {
//        List<PeopleDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDTO.class)
//                        .whereIn(PeopleDTO.DATE, date)
//
//        );
//        return areaEntities == null ? new ArrayList<PeopleDTO>() : areaEntities;
//    }
//    public PeopleDTO getCacheById(String id, String key) {
//        List<PeopleDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDTO.class)
//                        .whereIn(PeopleDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(PeopleDTO.KEY_CAHCE, key)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

//    public PeopleDTO getCacheByIdAndDate(String date, String id) {
//        List<PeopleDTO> areaEntities = mLiteOrm.query(
//                new QueryBuilder<>(PeopleDTO.class)
//                        .whereIn(PeopleDTO.ID_CACHE, id)
//                        .whereAppendAnd()
//                        .whereIn(PeopleDTO.DATE, date)
//        );
//        return (areaEntities == null || areaEntities.size() <=0) ? null : areaEntities.get(0);
//    }

    public PeopleDTO findById(FamilyDTO familyDTO) {
        try {
            ArrayList<PeopleDTO> list = mLiteOrm.query(
                    new QueryBuilder<>(PeopleDTO.class)
                            .whereEquals(PeopleDTO.ID_HO, familyDTO.getIDHO())
            );
            if(list.size()>0){
                return list.get(0);
            }else {
                return new PeopleDTO(familyDTO.getHOSO(), familyDTO.getIDHO());
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return new PeopleDTO(familyDTO.getHOSO(), familyDTO.getIDHO());
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(PeopleDTO.class)
                    .where(PeopleDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<PeopleDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(PeopleDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<PeopleDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(PeopleDTO.class)
                        .whereIn(PeopleDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
