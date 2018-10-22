package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class FamilyDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static FamilyDAO mInstance;

    public static synchronized FamilyDAO getInstance() {
        if (null == mInstance) {
            mInstance = new FamilyDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public FamilyDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<FamilyDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(FamilyDTO familyDTO) {
        if(checkIsExistDB(familyDTO.getIDHO())){
            return update(familyDTO);
        }else {
            return  mLiteOrm.insert(familyDTO);
        }
    }

    public int update(FamilyDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(FamilyDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
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
            return mLiteOrm.query(
                    new QueryBuilder<>(FamilyDTO.class)
                            .whereEquals(FamilyDTO.ID_FAMILY, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public ArrayList<FamilyDTO> findByUserId(String user) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(FamilyDTO.class)
                            .whereEquals(FamilyDTO.ID_INVESTIGATE, user)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public ArrayList<FamilyDTO> findByUserAndLocal(String user, String iddb) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(FamilyDTO.class)
                            .whereIn(FamilyDTO.ID_INVESTIGATE, user)
                    .whereAppendAnd()
                    .whereIn(FamilyDTO.ID_LOCALITY, iddb)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public ArrayList<FamilyDTO> findNewFamilyByUser(String user) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(FamilyDTO.class)
                            .whereIn(FamilyDTO.ID_INVESTIGATE, user)
                    .whereAppendAnd()
                    .whereIn(FamilyDTO.IS_NEW,true)

            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(FamilyDTO.class)
                    .where(FamilyDTO.ID_FAMILY, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
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
