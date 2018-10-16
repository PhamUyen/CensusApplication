package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class HouseDAO {
    private static final String TAG = "HouseDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static HouseDAO mInstance;

    public static synchronized HouseDAO getInstance() {
        if (null == mInstance) {
            mInstance = new HouseDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public HouseDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<HouseDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(HouseDTO deadDTO) {
        if(checkIsExistDB(deadDTO.getmID())){
            return update(deadDTO);
        }else {
            return mLiteOrm.insert(deadDTO);
        }
    }

    public int update(HouseDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(HouseDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(HouseDTO.class));
    }

    public List<HouseDTO> getAllArea() {
        List<HouseDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(HouseDTO.class)
        );
        return offlineEntities == null ? new ArrayList<HouseDTO>() : offlineEntities;
    }


    public HouseDTO findById(String id) {
        try {
            ArrayList<HouseDTO> list = mLiteOrm.query(
                    new QueryBuilder<>(HouseDTO.class)
                            .whereEquals(HouseDTO.ID_HO, id)
            );
            if(list.isEmpty()){
                return new HouseDTO(id);
            }else {
                return list.get(0);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
             return new HouseDTO(id);
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(HouseDTO.class)
                    .where(HouseDTO.ID_HO, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<HouseDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(HouseDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<HouseDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(HouseDTO.class)
                        .whereIn(HouseDTO.ID_HO, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
