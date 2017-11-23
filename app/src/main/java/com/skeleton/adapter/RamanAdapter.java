package com.skeleton.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.constant.AppConstant;
import com.skeleton.model.raman.Detail;
import com.skeleton.util.DateTimeUtil;

import java.util.List;

/**
 * Created by Raman on 22/10/17.
 */

public class RamanAdapter extends RecyclerView.Adapter<RamanAdapter.MyViewHolder> implements AppConstant {
    private Context mContext;
    private List<Detail> packagesList;


    /**
     * constructor
     *
     * @param mContext context
     */
    public RamanAdapter(final Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public RamanAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        mContext = parent.getContext();
        View myView;
        myView = LayoutInflater.from(mContext).inflate(R.layout.item_view_raman, parent, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final RamanAdapter.MyViewHolder holder, final int position) {
        holder.tvDate.setText(DateTimeUtil.getAppDate(packagesList.get(position).getStartDate()));
        holder.tvTask.setText(packagesList.get(position).getText());


    }

    @Override
    public int getItemCount() {
        if (packagesList == null) {
            return 0;
        } else {
            return packagesList.size();
        }
    }

    /**
     * set data coming from Api hit
     *
     * @param packages packages
     */
    public void setData(final List<Detail> packages) {
        packagesList = packages;
        notifyDataSetChanged();

    }

    /**
     * view holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate, tvTask;
        private RelativeLayout rlDetail;

        /**
         * view holder
         *
         * @param itemView item view
         */
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTask = (TextView) itemView.findViewById(R.id.tvTask);
        }

    }

}

