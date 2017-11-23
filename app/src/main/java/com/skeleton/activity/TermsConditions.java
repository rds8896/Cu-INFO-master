package com.skeleton.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.skeleton.R;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 **/
public class TermsConditions extends AppCompatActivity {

    private static final String TITLE = "Terms And Conditions";
    private Toolbar toolbar;
    private TextView tvTermsAndCond;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        toolbar = (Toolbar) findViewById(R.id.toolbar_t_and_c);
        tvTermsAndCond = (TextView) findViewById(R.id.tv_terms_and_conditions);
        setToolbar(toolbar);
    }

    /**
     * @param toolbar toolbar
     */
    private void setToolbar(final  Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatTextView) toolbar.findViewById(R.id.toolbar_title)).setText(TITLE);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() ==  android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
