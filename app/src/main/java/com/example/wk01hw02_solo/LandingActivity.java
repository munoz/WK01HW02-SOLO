package com.example.wk01hw02_solo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.wk01hw02_solo.MainActivity;

public class LandingActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.wk01hw02_solo.userIdKey";
    private static final String USER_NAME_KEY = "com.example.wk01hw02_solo.userNameKey";

    private TextView textViewResult;
    private TextView welcome;
    private int userId;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        wireupDisplay1();
        //wireupDisplay2();

    }

//    private void wireupDisplay2(){
//        textViewResult = findViewById(R.id.textView);
//        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
//
//        textViewResult.setText("userId: " + userId);
//
//    }

    private void wireupDisplay1(){
        textViewResult = findViewById(R.id.postsDisplay);
        welcome = findViewById(R.id.textView);
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        userName = getIntent().getStringExtra(USER_NAME_KEY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(userId);

        welcome.setText("WELCOME! " + userName + "\n\n");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    public static Intent intentFactory(Context context, int userId, String userName) {
        Intent intent = new Intent(context, LandingActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        intent.putExtra(USER_NAME_KEY, userName);

        return intent;
    }
}