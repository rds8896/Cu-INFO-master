package com.skeleton.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.skeleton.MyApplication;
import com.skeleton.R;
import com.skeleton.util.Log;
import com.skeleton.util.imagepicker.ImageChooser;

import java.io.File;
import java.util.List;

import static com.skeleton.constant.AppConstant.MAX_IMAGE_UPLOAD;
import static com.skeleton.constant.AppConstant.NO_VALUE_STRING;


/**
 * +++++++++++++++++++++++++++++++++
 * ++++++++++Raman Deep ++++++++++++
 * +++++++++++++++++++++++++++++++++
 */
public final class CarImageRecyclerAdapter extends RecyclerView.Adapter<CarImageRecyclerAdapter.CarImageHolder>
        implements ImageChooser.OnImageSelectListener {
    private static final String TAG = "CarImageRecyclerAdapter";
    private ImageChooser imageChooser;
    private List<String> imageUrl;
    private Context context;

    /**
     * @param context      context
     * @param imageChooser ImageChooser
     * @param imageUrl     List<String>
     */
    private CarImageRecyclerAdapter(final Context context
            , final ImageChooser imageChooser
            , final List<String> imageUrl) {
        this.imageChooser = imageChooser;
        this.context = context;
        this.imageUrl = imageUrl;
        setHasStableIds(true);
    }

    @Override
    public CarImageHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(MyApplication.getAppContext())
                .inflate(R.layout.item_single_image_select, parent, false);
        return new CarImageHolder(view);
    }

    @Override
    public void onBindViewHolder(final CarImageHolder holder, final int position) {
        if (!imageUrl.get(position).equalsIgnoreCase(NO_VALUE_STRING)) {
            Glide.with(context)
                    .load(new File(imageUrl.get(position)))
                    .into(holder.ivCar);
            holder.ivCross.setVisibility(View.VISIBLE);
        } else {
            holder.ivCar.setImageResource(R.drawable.add_image);
            holder.ivCross.setVisibility(View.GONE);
        }
        holder.ivCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                imageChooser.selectImage(CarImageRecyclerAdapter.this, holder.getAdapterPosition(), true);
            }
        });
        holder.ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                imageUrl.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrl.size();
    }

    @Override
    public void loadImage(final List<ChosenImage> list, final int index) {
        int count = 0;
        if (list.size() == 0) {
            return;
        }
        if (list.size() >= 1) {
            imageUrl.remove(index);
            Log.d(TAG, "list before :" + imageUrl);
            for (int i = 0; i < list.size(); i++) {
                if (imageUrl.size() < MAX_IMAGE_UPLOAD) {
                    count++;
                    imageUrl.add(index + i, list.get(i).getOriginalPath());
                    Log.d(TAG, "count " + count);
                }
            }
        }
        Log.d(TAG, "list after :" + imageUrl);
        notifyItemRangeChanged(index, count);
    }

    @Override
    public void croppedImage(final File mCroppedImage) {

    }

    /**
     * ViewHolder Class
     */
    class CarImageHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView ivCar, ivCross;

        /**
         * @param itemView View
         */
        public CarImageHolder(final View itemView) {
            super(itemView);
            ivCar = (AppCompatImageView) itemView.findViewById(R.id.iv_select);
            ivCross = (AppCompatImageView) itemView.findViewById(R.id.iv_cross);
        }


    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    /**
     * Builder Class
     */
    public static class Builder {
        private ImageChooser imageChoser;
        private List<String> imageUrl;
        private Context context;

        /**
         * @param context Context Object
         */
        public Builder(final Context context) {
            this.context = context;
        }

        /**
         * @param imageChooser ImageChooser
         * @return this
         */
        public Builder setImageChooser(final ImageChooser imageChooser) {
            this.imageChoser = imageChooser;
            return this;
        }


        /**
         * @param list initial image url list
         * @return Builder
         */
        public Builder setDataList(final List<String> list) {
            this.imageUrl = list;
            return this;
        }

        /**
         * @return CarImageRecyclerAdapter
         */
        public CarImageRecyclerAdapter build() {
            return new CarImageRecyclerAdapter(context, imageChoser, imageUrl);
        }
    }

}
