package com.uyenpham.censusapplication.models.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionDTO implements Parcelable {
    public final static String ID_CACHE = "id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_QUESTION = "question";
    public final static String COLUMN_TYPE = "type";
    private String id;
    private String name;
    private String question;
    private int type;
    private String options;
    private String placeHolder;
    private String answer;

    public QuestionDTO(String id, String name, String question, int type, String options,
                       String placeHolder, String answer) {
        this.id = id;
        this.name = name;
        this.question = question;
        this.type = type;
        this.options = options;
        this.placeHolder = placeHolder;
        this.answer = answer;
    }


    public QuestionDTO() {
    }

    protected QuestionDTO(Parcel in) {
        id = in.readString();
        name = in.readString();
        question = in.readString();
        type = in.readInt();
        options = in.readString();
        placeHolder = in.readString();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static final Creator<QuestionDTO> CREATOR = new Creator<QuestionDTO>() {
        @Override
        public QuestionDTO createFromParcel(Parcel in) {
            return new QuestionDTO(in);
        }

        @Override
        public QuestionDTO[] newArray(int size) {
            return new QuestionDTO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(question);
        parcel.writeInt(type);
        parcel.writeString(options);
        parcel.writeString(placeHolder);
    }
}
