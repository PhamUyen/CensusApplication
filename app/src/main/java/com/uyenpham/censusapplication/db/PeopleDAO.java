package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static PeopleDAO mInstance;

    private Dao<PeopleDTO,?> dao;
    public static synchronized PeopleDAO getInstance() {
        if (null == mInstance) {
            mInstance = new PeopleDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    public PeopleDAO(DBHelper helper) {
        try {
            dao = helper.getDao(PeopleDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void insert(PeopleDTO peopleDTO) {
        try {
            dao.createOrUpdate(peopleDTO);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(PeopleDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public List<PeopleDTO> getAllArea() {
        List<PeopleDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(PeopleDTO.class)
        );
        return offlineEntities == null ? new ArrayList<PeopleDTO>() : offlineEntities;
    }


    public PeopleDTO findById(FamilyDTO familyDTO) {
        try {
            List<PeopleDTO> list = dao.queryForEq(PeopleDTO.ID_HO, familyDTO.getIDHO());
            if(!list.isEmpty()){
                return list.get(0);
            }else {
                return new PeopleDTO(familyDTO.getHOSO(), familyDTO.getIDHO());
            }
        } catch (SQLException e) {
            Logger.e(e);
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
