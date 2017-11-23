package com.skeleton.util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.skeleton.R;

import static com.skeleton.constant.AppConstant.MIN_PASSWORD_LENGTH;
import static com.skeleton.util.Util.getStringRes;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */

public final class EditTextUtil {


    /**
     * Filter on editText to block space character
     */
    private static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(final CharSequence source,
                                   final int start,
                                   final int end,
                                   final Spanned dest,
                                   final int dstart,
                                   final int dend) {
            String blockCharacterSet = " ";
            if (source != null && blockCharacterSet.contains("" + source)) {
                return "";
            }
            return null;
        }
    };

    /**
     * Empty Constructor
     * not called
     */
    private EditTextUtil() {
    }

    /**
     * @param editText instance of that edittext on which no space functionality want
     */
    public static void blockSpace(final EditText editText) {
        editText.setFilters(new InputFilter[]{filter});
    }


//    ======================================== OTP MOVE =============================================


    /**
     * Adds automation to the editText moving to next or previous
     *
     * @param mSize    size after which you want to move to next editText
     * @param editText reference to all the edit to move next or previous location
     */

    public static void moveFrontAndBack(final int mSize, final EditText... editText) {
        for (int pos = 0; pos < (editText.length - 1); pos++) {
            moveToNextEt(editText[pos], editText[pos + 1], mSize);
        }
        for (int pos = 1; pos < editText.length; pos++) {
            moveToPreviousEt(editText[pos], editText[pos - 1]);
        }
    }

    /**
     * adds Functionality to move automatically to next field or edit text
     *
     * @param mCurrentEt current reference to EditText
     * @param mNextEt    refernce to next editText shifted to
     * @param mSize      size after which to move to the next position
     */
    public static void moveToNextEt(final EditText mCurrentEt, final EditText mNextEt, final int mSize) {
        mCurrentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (mCurrentEt.getText().toString().length() == mSize) {
                    mNextEt.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });
    }


    /**
     * adds Functionality to move automaticaly to previous field or edit text
     *
     * @param mCurrentEt current reference to EditText
     * @param mPrevEt    refernce to next edittext shifted to
     */
    public static void moveToPreviousEt(final EditText mCurrentEt, final EditText mPrevEt) {
        mCurrentEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(final View view, final int keyCode, final KeyEvent keyEvent) {
                Log.e("onKey pressed", "==" + keyCode);
                Log.e("text", "==" + mCurrentEt.getText().toString());
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && mCurrentEt.getText().toString().isEmpty()) {
                    mPrevEt.requestFocus();
                }
                return false;
            }
        });
    }

    /**
     * @param editText EditText on which watcher need to added
     */
    public static void addPasswordPatternWatcher(final EditText editText) {
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

                }

                @Override
                public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                    if (!s.toString().matches("(^.*[A-Z].*$)")) {
                        setEditTextError(editText, getStringRes(R.string.error_password_contain_Capital_alpha));
                    } else if (!s.toString().matches("(^.*[a-z].*$)")) {
                        setEditTextError(editText, getStringRes(R.string.error_password_contain_small_alpha));
                    } else if (!s.toString().matches("(^.*[\\W]).*$")) {
                        setEditTextError(editText, getStringRes(R.string.error_password_contain_special_char));
                    } else if (!s.toString().matches("(^.*[\\d].*$)")) {
                        setEditTextError(editText, getStringRes(R.string.error_password_contain_number));
                    } else if (s.toString().length() < MIN_PASSWORD_LENGTH) {
                        setEditTextError(editText, getStringRes(R.string.error_password_must_be_greater_than_5_character));
                    } else {
                        removeErrorEditText(editText);
                    }

                }

                @Override
                public void afterTextChanged(final Editable s) {

                }
            });
        }
    }

    /**
     * @param editText EditText on which match is performed
     * @param toMatch  EditText to which match is performed
     */
    public static void addTextMatchWatcher(final EditText editText, final EditText toMatch) {
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

                }

                @Override
                public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

                }

                @Override
                public void afterTextChanged(final Editable s) {
                    if (!s.toString().trim().equals(toMatch.getText().toString().trim())) {
                        setEditTextError(editText, getStringRes(R.string.error_password_mismatch));
                    } else {
                        removeErrorEditText(editText);
                    }
                }
            });
        }
    }


    /**
     * Function to show error on EditText label through TextInputLayout
     *
     * @param editTextError EditText objet
     * @param error         String Error Message to show
     */
    public static void setEditTextError(final EditText editTextError, final String error) {

        editTextError.setError(error);
    }

    /**
     * Function to hide error on EditText label through TextInputLayout
     *
     * @param editText EditText objet
     */
    public static void removeErrorEditText(final EditText editText) {

        editText.setError(null);
    }


    //    ======================================== OTP MOVE END =============================================


}
