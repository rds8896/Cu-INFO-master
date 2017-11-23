package com.skeleton.util.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.skeleton.R;

import java.util.ArrayList;

import static com.skeleton.constant.AppConstant.DIM_AMOUNT;

/**
 * class CommonDialogWithList
 */
public final class CommonDialogWithList {


    private final Activity activity;
    private DialogListAdapter dialogListAdapter;
    private ListView dlgPriorityLvw;
    private OnListItemClickListener nnListItemClickListener;

    /**
     * @param act , Activity instance
     */
    private CommonDialogWithList(final Activity act) {
        activity = act;
    }

    /**
     * With common dialog with list.
     *
     * @param activity the activity
     * @return common dialog with list
     */
    public static CommonDialogWithList with(final Activity activity) {
        return new CommonDialogWithList(activity);
    }

    /**
     * Show.
     *
     * @param header    the header
     * @param arrayList the array list
     * @param listener  the listener
     */
    public void show(final String header, final ArrayList<String> arrayList, final OnListItemClickListener listener) {
        try {
            nnListItemClickListener = listener;

            final Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.dialog_with_list);


            final WindowManager.LayoutParams layoutParams = dialog.getWindow()
                    .getAttributes();
            layoutParams.dimAmount = DIM_AMOUNT;
            dialog.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.setCancelable(true);


            dialog.setCanceledOnTouchOutside(true);

            dlgPriorityLvw = (ListView) dialog.findViewById(R.id.dlg_priority_lvw);

            // ListView
            dialogListAdapter = new DialogListAdapter(activity);
            dialogListAdapter.setData(arrayList);

            dlgPriorityLvw.setAdapter(dialogListAdapter);
            //ListView
            dlgPriorityLvw
                    .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(final AdapterView<?> arg0, final View arg1,
                                                final int arg2, final long arg3) {
                            nnListItemClickListener.onListItemSelected(arg2, arrayList.get(arg2));
                            dialog.dismiss();
                        }
                    });

            dialog.show();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The interface On list item click listener.
     */
    public interface OnListItemClickListener {
        /**
         * On list item selected.
         *
         * @param pos        the pos
         * @param itemString the item string
         */
        void onListItemSelected(int pos, String itemString);
    }
}