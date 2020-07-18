package com.example.giaodien_v1.abstract_class;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaodien_v1.R;

public abstract class ActivityBase extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialogLoading();
    }

    private void initDialogLoading() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(R.string.loading);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    protected void showDialogLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    protected void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
