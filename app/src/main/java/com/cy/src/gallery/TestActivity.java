package com.cy.src.gallery;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Fresco.initialize(getApplication());
        Uri uris[] = new Uri[]{
                Uri.parse("http://f.hiphotos.baidu.com/image/h%3D200/sign=e648595ff01986185e47e8847aed2e69/0b46f21fbe096b63a377826e04338744ebf8aca6.jpg"),
                Uri.parse("http://photo.enterdesk.com/2011-2-16/enterdesk.com-1AA0C93EFFA51E6D7EFE1AE7B671951F.jpg"),
                Uri.parse("http://img1.mydrivers.com/img/20121009/621aeecd83eb43d5b738dff93b4a0002.jpg"),
                Uri.parse("http://pic1.desk.chinaz.com/file/10.03.10/5/rrgaos56.jpg"),
                Uri.parse("http://pic24.nipic.com/20121012/5955207_230020121000_2.jpg"),
        };
        PhotoViewActivity.startActivity(this, 0, uris);
    }
}
