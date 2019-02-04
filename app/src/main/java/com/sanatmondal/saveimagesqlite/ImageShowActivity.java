package com.sanatmondal.saveimagesqlite;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanatmondal.saveimagesqlite.DBHelper.DBHelper;
import com.sanatmondal.saveimagesqlite.Utils.Utils;

public class ImageShowActivity extends AppCompatActivity {

    EditText edt_name;
    Button btn_select;
    ImageView img_view;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        dbHelper = new DBHelper(this);

        edt_name = (EditText) findViewById(R.id.edt_name);
        btn_select = (Button) findViewById(R.id.btn_select);
        img_view = (ImageView) findViewById(R.id.img_view);

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] data = dbHelper.getBitmapByName(edt_name.getText().toString());
                if(data != null){
                    Bitmap bitmap = Utils.getImage(data);
                    img_view.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(getApplicationContext(), "Image Not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
