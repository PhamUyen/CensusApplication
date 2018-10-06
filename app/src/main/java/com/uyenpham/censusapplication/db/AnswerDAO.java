package com.uyenpham.censusapplication.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {
    private static final String TAG = "QuestionDAO";

    private Context mContext;
    private LiteOrm mLiteOrm;

    private static AnswerDAO mInstance;

    public static synchronized AnswerDAO getInstance() {
        if (null == mInstance) {
            mInstance = new AnswerDAO(App.getInstance(), LiteOrmHelper.getInstance());
        }
        return mInstance;
    }

    public AnswerDAO(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    public long insertAllOffline(List<AnswerDTO> offlineEntities) {
        if (null == offlineEntities) return -1;

        deleteAllOffline();

        long result = mLiteOrm.save(offlineEntities);

        return result;
    }

    public long insert(AnswerDTO answerDTO) {
        if (checkIsExistDB(answerDTO.getId())) {
            return mLiteOrm.update(answerDTO);
        } else {
            return mLiteOrm.insert(answerDTO);
        }
    }

    public int update(AnswerDTO cacheEntity) {
        int result = mLiteOrm.update(cacheEntity);
        return result;
    }

    public int delete(AnswerDTO cacheEntity) {
        int result = mLiteOrm.delete(cacheEntity);
        return result;
    }

    private void deleteAllOffline() {
        mLiteOrm.delete(WhereBuilder
                .create(AnswerDTO.class));
    }

    public List<AnswerDTO> getAllAnswer() {
        List<AnswerDTO> answers = mLiteOrm.query(
                new QueryBuilder<>(AnswerDTO.class)
        );
        return answers == null ? new ArrayList<AnswerDTO>() : answers;
    }


    public AnswerDTO findById(String idQuest, String idHo) {
        idQuest = idQuest.startsWith("m") ?idQuest : "m"+idQuest;
        String id = idHo+idQuest;
        try {
            ArrayList<AnswerDTO> list =  mLiteOrm.query(
                    new QueryBuilder<>(AnswerDTO.class)
                            .whereEquals(AnswerDTO.COLUMN_ID, id)
            );
            if(list.size() >0){
                return list.get(0);
            }else {
                return new AnswerDTO(idHo, idQuest, id);
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return new AnswerDTO(idHo, idQuest, id);
        }
    }
    public AnswerDTO findByIdQuestion(String id) {
        try {
            return mLiteOrm.query(
                    new QueryBuilder<>(AnswerDTO.class)
                            .whereEquals(AnswerDTO.COLUMN_QUESTION_ID, id)
            ).get(0);
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return new AnswerDTO(id);
        }
    }

    public int deleteByid(String key) {
        try {
            return mLiteOrm.delete(new WhereBuilder(AnswerDTO.class)
                    .where(AnswerDTO.COLUMN_ID, key)
            );
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
            return -1;
        }
    }

    public void deleteByKey(List<AnswerDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                mLiteOrm.delete(list.get(i));
            }
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage(), e);
        }
    }

    public void deleteAll() {
        mLiteOrm.deleteAll(AnswerDTO.class);
    }


    public boolean checkIsExistDB(String id) {
        List<AnswerDTO> areaEntities = mLiteOrm.query(
                new QueryBuilder<>(AnswerDTO.class)
                        .whereIn(AnswerDTO.COLUMN_ID, id)
        );
        return areaEntities != null && areaEntities.size() > 0;
    }
}
