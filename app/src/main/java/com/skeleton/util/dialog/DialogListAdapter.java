package com.skeleton.util.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skeleton.R;

import java.util.ArrayList;

/**
 * class DialogListAdapter
 */
public class DialogListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    // private final DateDifferentCalculation differentCalculation;
    private ArrayList<String> array = new ArrayList<>();
    //  private Activity activity;

    /**
     * Instantiates a new Dialog list adapter.
     *
     * @param activity the activity
     */
    public DialogListAdapter(final Activity activity) {

        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //  this.activity = activity;
        //  differentCalculation = new DateDifferentCalculation();
    }

    /**
     * Sets data.
     *
     * @param jobsArrayList the jobs array list
     */
    public void setData(final ArrayList<String> jobsArrayList) {

        this.array = jobsArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(final int position) {
        return position;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View item = convertView;
        final ViewHolder viewHolder;
        if (convertView == null) {
            item = mInflater.inflate(R.layout.dialog_list_item, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) item.findViewById(R.id.tv_doc_name);


            viewHolder.pos = position;

            item.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) item.getTag();
        }

        viewHolder.name.setText(array.get(position));


        return item;
    }

    /**
     * The type View holder.
     */
    static class ViewHolder {

        private TextView name;

        private int pos;

        /**
         * getter of name TextView
         *
         * @return name ,  TextView instance
         */
        public TextView getName() {
            return name;
        }

        /**
         * setter of name TextView
         * @param name ,  TextView instance
         */
        public void setName(final TextView name) {
            this.name = name;
        }

        /**
         * getter of Position
         * @return the position
         */
        public int getPos() {
            return pos;
        }

        /**
         * setter of position
         * @param pos , position in adapter
         */
        public void setPos(final int pos) {
            this.pos = pos;
        }

    }
}

