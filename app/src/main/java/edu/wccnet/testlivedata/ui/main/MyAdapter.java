package edu.wccnet.testlivedata.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import edu.wccnet.testlivedata.MovieFragment;
import edu.wccnet.testlivedata.MovieItem;
import edu.wccnet.testlivedata.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<MovieItem> mDataset;
    Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<MovieItem> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list, parent, false);
        MyViewHolder vh = new MyViewHolder(cv, mDataset, parent.getContext());
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.mImageView.setImageBitmap(mDataset.get(position).getPoster());
        holder.id = position;
    }

    // Replace the contents of a view (invoked by the layout manager)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView mCardView;
        public TextView mTextView;
        public ImageView mImageView;
        int id = 0;
        private List<MovieItem> mDataset;
        private Context context;

        public MyViewHolder(CardView c, List<MovieItem> mDataset, Context context) {
            super(c);
            mCardView = c;
            mTextView = mCardView.findViewById(R.id.info_text);
            mImageView = mCardView.findViewById(R.id.poster);
            this.mDataset = mDataset;
            mCardView.setOnClickListener(this);
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, MovieFragment.newInstance(mDataset.get(position), "Test")).addToBackStack("MovieFragment").commit();
        }
    }
}
