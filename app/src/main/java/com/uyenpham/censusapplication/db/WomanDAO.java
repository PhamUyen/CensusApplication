package com.uyenpham.censusapplication.db;

import com.j256.ormlite.dao.Dao;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WomanDAO {
    private static final String TAG = "WomanDAO";

    private static WomanDAO mInstance;
    private Dao<WomanDTO,?> dao;

    public static synchronized WomanDAO getInstance() {
        if (null == mInstance) {
            mInstance = new WomanDAO(new DBHelper(App.getInstance()));
        }
        return mInstance;
    }

    public WomanDAO(DBHelper helper) {
        try {
            dao = helper.getDao(WomanDTO.class);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void insert(WomanDTO womanDTO) {
        try {
            dao.createOrUpdate(womanDTO);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }


    public void delete(WomanDTO cacheEntity) {
        try {
            dao.delete(cacheEntity);
        } catch (SQLException e) {
            Logger.e(e);
        }
    }



    public List<WomanDTO> findById(String id) {
          try {
              return dao.queryForEq(WomanDTO.ID_HO, id);
        } catch (SQLException e) {
            Logger.e(e);
            return new ArrayList<>();
        }
    }
}
