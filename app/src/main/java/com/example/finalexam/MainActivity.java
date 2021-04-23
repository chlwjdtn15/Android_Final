package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView nameTv;
    TextView ageTV;
    TextView isStudentTV;
    TextView scoresTV;
    TextView mathScoreTV;
    TextView programScoreTV;

    MyVeiwModel veiwModel;

    final String FILE_NAME = "finals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTv = findViewById(R.id.name_TV);
        ageTV = findViewById(R.id.age_TV);
        isStudentTV = findViewById( R.id.isStudent_TV);
        scoresTV = findViewById(R.id.score_TV);
        mathScoreTV = findViewById(R.id.math_TV);
        programScoreTV = findViewById(R.id.program_TV);

        veiwModel = new ViewModelProvider(this).get(MyVeiwModel.class);



    }

    public void loadData(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(readJSONFile());

                            String actualMathScore = "";
                            String acutalProgramScore = "";

                            String name = jsonObject.getString("name");
                            String age =  jsonObject.getString("age");
                            String isStudent = jsonObject.getString("isStudent");
                            Log.d("MainActivity", "RUN: item = " + name + " " + age +  " " + isStudent);

                            JSONArray scoreInformation = jsonObject.getJSONArray( "scores");
                            for (int i = 0; i < scoreInformation.length(); i++) {
                                JSONObject e = scoreInformation.getJSONObject(i);

                                if(i == 0) {
                                    String mathScore = e.getString("Math");

                                    actualMathScore = mathScore;
                                    Log.d("MainActivity", "RUN: item = " + mathScore);
                                }
                                else if (i == 1) {
                                    String programScore = e.getString("Programming");

                                    acutalProgramScore = programScore;

                                    Log.d("MainActivity", "RUN: item = " + programScore);
                                }




                            }

                            SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("username", name);
                            editor.putString("userage", age);
                            editor.putString("isStudent", isStudent);
                            editor.putString("mathScore", actualMathScore);
                            editor.putString("programScore", acutalProgramScore);
                            editor.commit();



                            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            
                            
                            

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.d("MainActivity", "Error in JSON");
                        }

                    }
                });

            }
        }).start();



    }

    public void displayData(View view) {
        veiwModel = new ViewModelProvider(this).get(MyVeiwModel.class);
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);


        String name = preferences.getString("username", "no_name");
        String age = preferences.getString("userage", "no_age");
        String isStudent = preferences.getString("isStudent", "no_name");
        String mathScore = preferences.getString("mathScore", "no_name");
        String programScore = preferences.getString("programScore", "no_name");
        String score = "Score";

        veiwModel.setInformation(name, age, isStudent, mathScore, programScore, score);

        nameTv.setText(veiwModel.getName());
        ageTV.setText(veiwModel.getAge());
        isStudentTV.setText(veiwModel.getIsStudent());
        mathScoreTV.setText(veiwModel.getMathScore());
        programScoreTV.setText(veiwModel.getProgramScore());
        scoresTV.setText(veiwModel.getScore());





    }

    public String readJSONFile(){

        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("user.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();

            // convert byte to string
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }


}