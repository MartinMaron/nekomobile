package de.eneko.nekomobile.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import de.eneko.nekomobile.R;


/**
 * Activity that displays the content of a path in dropbox and lets users navigate folders,
 * and upload/download files
 */
public class PictureViewActivity extends AppCompatActivity {
    private static final String TAG = PictureViewActivity.class.getName();
    protected PhotoView imageView = null;
    protected MenuItem showBookPictureMenuItem = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // erstellen der Activity
        setContentView(R.layout.picture_view);
        setImageView(findViewById(R.id.imageView));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Bundle bundle = getIntent().getExtras();
        int pictureId = bundle.getInt("PICTURE_ID");
        //getImageView().setImageResource(pictureId);
        Glide.with(this).load(pictureId).into(imageView);

    }

    public PhotoView getImageView() {
        return imageView;
    }

    public void setImageView(PhotoView imageView) {
        this.imageView = imageView;
    }
}
