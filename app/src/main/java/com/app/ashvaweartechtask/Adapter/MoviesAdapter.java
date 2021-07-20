package com.app.ashvaweartechtask.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ashvaweartechtask.Model.Result;
import com.app.ashvaweartechtask.R;
import com.app.ashvaweartechtask.databinding.MoviesRowBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private Context context;
    private List<Result> resultList;
    private LayoutInflater layoutInflater;
    private Click click;

    public MoviesAdapter(Context context, List<Result> resultList,Click click) {
        this.context = context;
        this.resultList = resultList;
        this.click = click;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesRowBinding binding;
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.movies_row,parent,false);
        return new MoviesAdapter.MoviesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        Result data = resultList.get(position);
        Log.d("image", "onBindViewHolder: "+data.getPosterPath());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w780"+data.getPosterPath())
                .placeholder(R.drawable.ic_image)
                .into(holder.binding.iv);
        holder.binding.tv1.setText(data.getTitle());
        holder.binding.tv2.setText(data.getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        MoviesRowBinding binding;
        public MoviesViewHolder(@NonNull MoviesRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.rootLayout.setOnClickListener(v -> {
                click.getClick(getAdapterPosition());
            });
        }
    }
    public interface Click{
        void getClick(int position);
    }
}
