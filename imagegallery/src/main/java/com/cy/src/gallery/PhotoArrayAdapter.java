package com.cy.src.gallery;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import me.relex.photodraweeview.PhotoDraweeView;

//import uk.co.senab.photoview.PhotoView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
//import com.cy.src.photos.R;

public class PhotoArrayAdapter<T> extends BasePagerAdapter<T> {


    public PhotoArrayAdapter(Activity act) {
        super(act);

    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Uri uri = (Uri) mListData.get(position);//.toString();
//        if (!u.startsWith("file") && !u.startsWith("http")) {
//            u = "file://" + u;
//        }
        View view = mAct.getLayoutInflater().inflate(R.layout.item_photoview, container, false);

        Holder holder = new Holder(view);
        holder.setData(uri);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public static class Holder {

        private View mView;
        private PhotoDraweeView mSdvImage;

        ProgressBar mProgressBar;

        public Holder(View view) {
            mView = view;
            mSdvImage = (PhotoDraweeView) mView.findViewById(R.id.sdv_image);
            mProgressBar = (ProgressBar) mView.findViewById(R.id.progressBar1);
        }

        public void setData(Uri data) {
            mProgressBar.setVisibility(View.VISIBLE);
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(data);
            controller.setOldController(mSdvImage.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null || mSdvImage == null || mProgressBar == null) {
                        return;
                    }
                    mSdvImage.update(imageInfo.getWidth(), imageInfo.getHeight());
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(String id, Throwable throwable) {
                    super.onFailure(id, throwable);
                    if (mView == null || mProgressBar == null) {
                        return;
                    }
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(mView.getContext(), R.string.loading_fail, Toast.LENGTH_SHORT).show();
                }
            });
            mSdvImage.setController(controller.build());
        }


    }


}
