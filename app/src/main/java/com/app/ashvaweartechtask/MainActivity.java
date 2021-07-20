package com.app.ashvaweartechtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.ashvaweartechtask.Adapter.MoviesAdapter;
import com.app.ashvaweartechtask.Model.Example;
import com.app.ashvaweartechtask.Model.Result;
import com.app.ashvaweartechtask.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.Click{
    private ActivityMainBinding binding;
    private List<Result> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getMovies();
        binding.progressBar.setVisibility(View.VISIBLE);

    }
    private void getMovies(){
        Call<Example> call = RetrofitClient.getInstance().getMyApi().getMovies();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    Log.d("movies", "onResponse: "+response.body().getResults());
                    resultList = response.body().getResults();
                    binding.progressBar.setVisibility(View.GONE);
                    binding.rvMovies.setHasFixedSize(true);
                    binding.rvMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.rvMovies.setAdapter(new MoviesAdapter(MainActivity.this,resultList,MainActivity.this));

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void getClick(int position) {
        startActivity(new Intent(MainActivity.this,MoviesDetailsActivity.class));

    }
}