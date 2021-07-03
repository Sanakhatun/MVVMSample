package com.professional.mvvmsample.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.professional.mvvmsample.R;
import com.professional.mvvmsample.repository.local.database.Sample;
import com.professional.mvvmsample.utilities.NetworkUtils;

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewHolder> {

    private Context context;

    private List<Sample> dataList;

    private CustomRecyclerViewHolder customRecyclerViewHolder;

    private static final String TAG = CustomRecyclerViewAdapter.class.getSimpleName();

    private DisplayImageOptions displayImageOptions;

    private ImageLoader imageLoader;

    public CustomRecyclerViewAdapter(Context context, List<Sample> dataList) {
        this.context = context;
        this.dataList = dataList;

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @NonNull
    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View rootView = inflater.inflate(R.layout.layout_custom_row, parent, false);
            customRecyclerViewHolder = new CustomRecyclerViewHolder(rootView);
        } catch (Exception e) {
            Log.e(TAG, String.valueOf(e.getStackTrace()));
        }

        return customRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomRecyclerViewHolder holder, int position) {

        if (dataList != null && dataList.size() > 0) {

            Sample currentItem = dataList.get(position);

            holder.tv_title.setText(currentItem.getTitle());
            holder.tv_shortDescription.setText(currentItem.getShortDescription());
            holder.tv_fundedAmt.setText(String.valueOf(currentItem.getCollectedValue()));
            holder.tv_goalsAmt.setText(String.valueOf(currentItem.getTotalValue()));
            holder.tv_endsInDuration.setText(NetworkUtils.getDaysDifference(currentItem.getStartDate(), currentItem.getEndDate()));

            /*Offline Caching */
            displayImageOptions = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_launcher_foreground)
                    .showImageForEmptyUri(R.drawable.ic_launcher_background)
                    .cacheInMemory()
                    .cacheOnDisk(true)
                    .build();

            imageLoader.displayImage(currentItem.getMainImageURL(), holder.iv_image, displayImageOptions);

        }

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
