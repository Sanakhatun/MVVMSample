package com.professional.mvvmsample.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.professional.mvvmsample.R;

public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView iv_image;

    TextView tv_title;

    TextView tv_shortDescription;

    TextView tv_fundedAmt;

    TextView tv_goalsAmt;

    TextView tv_endsInDuration;

    Button btn_pledge;

    public CustomRecyclerViewHolder(View itemView) {
        super(itemView);

        iv_image = itemView.findViewById(R.id.iv_image);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_shortDescription = itemView.findViewById(R.id.tv_shortDescription);
        tv_fundedAmt = itemView.findViewById(R.id.tv_fundedAmt);
        tv_goalsAmt = itemView.findViewById(R.id.tv_goalsAmt);
        tv_endsInDuration = itemView.findViewById(R.id.tv_endsInDuration);
        btn_pledge = itemView.findViewById(R.id.btn_pledge);

    }
}
