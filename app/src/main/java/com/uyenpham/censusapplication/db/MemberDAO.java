package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private static final String TAG = "FamilyDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;
    private Dao<MemberDTO,?>dao;

    private static MemberDAO mInstance;

    public static synchronized MemberDAO getInstance() {
        if (null == mInstance) {
            mInstance = new MemberDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    private MemberDAO(DBHelper helper) {
        try {
            dao = helper.getDao(MemberDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public void insert(MemberDTO cacheEntity) {
        try {
            dao.createOrUpdate(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }

    public void delete(MemberDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public List<MemberDTO> getAllArea() {
        List<MemberDTO> offlineEntities = mLiteOrm.query(
                new QueryBuilder<>(MemberDTO.class)
        );
        return offlineEntities == null ? new ArrayList<MemberDTO>() : offlineEntities;
    }
    public MemberDTO findChuHo() {
        try {
            List<MemberDTO> list = dao.queryForEq(MemberDTO.C02, 1);
            if(!list.isEmpty()){
                return list.get(0);
            }else {
                return null;
            }
        } catch (Exception e) {
            Logger.e(e);
            return null;
        }
    }
    public MemberDTO findById(String id) {
        try {
            List<MemberDTO> list = dao.queryForEq(MemberDTO.ID_MEMBER, id);
            if(!list.isEmpty()){
                return list.get(0);
            }else {
                return new MemberDTO();
            }
        } catch (Exception e) {
            Logger.e(e);
            return new MemberDTO();
        }
    }
    public List<MemberDTO> findByIdHo(String id) {
        try {
            return dao.queryForEq(MemberDTO.ID_HO, id);
        } catch (SQLException e) {
            Logger.e(e);
            return new ArrayList<>();
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
