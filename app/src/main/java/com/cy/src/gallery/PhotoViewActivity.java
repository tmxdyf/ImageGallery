package com.cy.src.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.cy.src.photos.R;


/**
 * @author CY
 */
public class PhotoViewActivity extends Activity {

    public static final String EXTRA_PHOTOARRAY = "photo_array";
    public static final String EXTRA_PHOTOARRAY_START_INDEX = "photo_array_startindex";
    private Uri mPhotoArray[];
    private int startIndex;

    Activity mAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAct = this;
        if (getIntent().hasExtra(EXTRA_PHOTOARRAY)) {
            Parcelable parcelable[] = getIntent().getParcelableArrayExtra(EXTRA_PHOTOARRAY);
            mPhotoArray = new Uri[parcelable.length];
            System.arraycopy(parcelable, 0, mPhotoArray, 0, parcelable.length);
            startIndex = getIntent().getIntExtra(EXTRA_PHOTOARRAY_START_INDEX, 0);
            if (startIndex >= mPhotoArray.length || startIndex < 0) {
                startIndex = 0;
            }
            showPhotoArray();
        } else if (getIntent().getData() != null) {
            showNormalImage(getIntent().getData());
        }

    }

    public static void startActivity(Context context, int startIndex, Uri... paths) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        if (paths != null) {
            if (paths.length == 1) {
                intent.setData(paths[0]);
            } else if (paths.length > 1) {
                intent.putExtra(EXTRA_PHOTOARRAY, paths);
                intent.putExtra(EXTRA_PHOTOARRAY_START_INDEX, startIndex);
            }
        }
        context.startActivity(intent);
    }


    private void showNormalImage(Uri path) {
        View view = mAct.getLayoutInflater().inflate(R.layout.item_photoview, null);
        PhotoArrayAdapter.Holder holder = new PhotoArrayAdapter.Holder(view);
        holder.setData(path);
        setContentView(view);
    }


    private void showPhotoArray() {

        FrameLayout fl = new FrameLayout(mAct);
        fl.setBackgroundColor(0xff000000);
        PhotoViewPager viewPager = new PhotoViewPager(mAct);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        fl.addView(viewPager, params);

        final TextView tvIndex = new TextView(mAct);
        tvIndex.setText((startIndex + 1) + "/" + mPhotoArray.length);
        tvIndex.setTextColor(Color.WHITE);
        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.setMargins(0, 0, 0, dip2px(mAct, 60));
        fl.addView(tvIndex, params);

        setContentView(fl);

        PhotoArrayAdapter<Uri> adapter = new PhotoArrayAdapter(mAct);
        adapter.setListData(mPhotoArray);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(startIndex);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                tvIndex.setText((arg0 + 1) + "/" + mPhotoArray.length);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    private int dip2px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dip + 0.5f);
    }

    private void showToast(CharSequence text) {
        Toast.makeText(mAct, text, Toast.LENGTH_SHORT).show();
    }

    private void showToastInThread(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(text);
            }
        });
    }
}


