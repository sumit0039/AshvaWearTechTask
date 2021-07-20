package com.app.ashvaweartechtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.app.ashvaweartechtask.Model.DetailsExample;
import com.app.ashvaweartechtask.databinding.ActivityMoviesDetailsBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDetailsActivity extends AppCompatActivity {
    private ActivityMoviesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movies_details);
        getMoviesDetails();
    }
    private void getMoviesDetails(){
        Call<DetailsExample> call = RetrofitClient.getInstance().getMyApi().getMoviesDetails();
        call.enqueue(new Callback<DetailsExample>() {
            @Override
            public void onResponse(Call<DetailsExample> call, Response<DetailsExample> response) {
                if (response.isSuccessful()){
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/w780"+response.body().getPosterPath())
                            .placeholder(R.drawable.ic_image)
                            .into(binding.detailsIv);
                    binding.tvTitle.setText(response.body().getTitle());
                    binding.tvRelease.setText(response.body().getStatus()+" on "+response.body().getReleaseDate());
                    binding.tvGenres.setText(response.body().getGenres().get(0).getName()+ " , " +response.body().getSpokenLanguages().get(0).getEnglishName());
                    binding.tvDes.setText(response.body().getOverview());
                }
            }

            @Override
            public void onFailure(Call<DetailsExample> call, Throwable t) {

            }
        });
    }
}