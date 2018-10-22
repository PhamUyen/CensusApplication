package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.locality.LocalityDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;


public class LocalityDAO {
    private static final String TAG = "LocalityDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static LocalityDAO mInstance;

    public static synchronized LocalityDAO getInstance() {
        if (null == mInstance) {
            mInstance = new LocalityDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public LocalityDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<LocalityDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(LocalityDTO familyDTO) {
        if(checkIsExistDB(familyDTO.getIDDB())){
            return update(familyDTO);
        }else {
            return  mLiteOrm.insert(familyDTO);
        }
    }
    public int update(LocalityDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(LocalityDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
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
            return mLiteOrm.query(
                    new QueryBuilder<>(LocalityDTO.class)
                            .whereEquals(LocalityDTO.ID_LOCALITY, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public ArrayList<LocalityDTO> findByUserId(String user) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(LocalityDTO.class)
                            .whereEquals(LocalityDTO.ID_USER, user)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return null;
        }
    }
    public ArrayList<LocalityDTO> findByUserAndLocal(String user, String iddb) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(LocalityDTO.class)
                            .whereIn(LocalityDTO.ID_USER, user)
                    .whereAppendAnd()
                    .whereIn(LocalityDTO.ID_LOCALITY, iddb)
            );
        } catch (Exception e) {
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
