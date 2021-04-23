package com.example.finalexam;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyVeiwModel extends ViewModel {


    String name;
    String age;
    String isStudent;
    String mathScore;
    String programScore;
    String score;

    MutableLiveData<String> mName = new MutableLiveData<>();
    MutableLiveData<String> mAge = new MutableLiveData<>();
    MutableLiveData<String> mIsStudent = new MutableLiveData<>();
    MutableLiveData<String> mMathScore = new MutableLiveData<>();
    MutableLiveData<String> mProgramScore = new MutableLiveData<>();
    MutableLiveData<String> mScore = new MutableLiveData<>();

    public void setmName(String mName) {
        this.mName.postValue(mName);
    }
    public void setmAge(String mAge) {
        this.mName.postValue(mAge);
    }
    public void setmIsStudent(String mIsStudent) {
        this.mName.postValue(mIsStudent);
    }
    public void setmMathScore(String mMathScore) {
        this.mName.postValue(mMathScore);
    }
    public void setmProgramScore(String mProgramScore) {
        this.mName.postValue(mProgramScore);
    }
    public void setMathScore(String mScore) {
        this.mName.postValue(mScore);
    }

    public void setInformation(String name, String age, String isStudent, String mathScore, String programScore, String score)
    {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
        this.mathScore = mathScore;
        this.programScore = programScore;
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getIsStudent() {
        return isStudent;
    }

    public String getMathScore() {
        return mathScore;
    }

    public String getProgramScore() {
        return programScore;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "MyVeiwModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", isStudent='" + isStudent + '\'' +
                ", mathScore='" + mathScore + '\'' +
                ", programScore='" + programScore + '\'' +
                ", score='" + score + '\'' +
                '}';
    }


}
