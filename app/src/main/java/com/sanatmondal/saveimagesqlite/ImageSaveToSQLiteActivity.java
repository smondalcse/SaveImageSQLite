package com.sanatmondal.saveimagesqlite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImageSaveToSQLiteActivity extends AppCompatActivity {

    Button show_image, select_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_save_to_sqlite);

        show_image = (Button) findViewById(R.id.show_image);
        select_image = (Button) findViewById(R.id.select_image);

        show_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImageSaveToSQLiteActivity.this, SelectActivity.class));
            }
        });

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImageSaveToSQLiteActivity.this, ImageShowActivity.class));
            }
        });
    }
}
