package com.sanatmondal.saveimagesqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanatmondal.saveimagesqlite.DBHelper.DBHelper;
import com.sanatmondal.saveimagesqlite.Utils.Utils;

public class SelectActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 777;
    ImageView img_view;
    Button btn_choose, btn_save;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        dbHelper = new DBHelper(this);

        img_view = (ImageView) findViewById(R.id.img_view);
        btn_choose = (Button) findViewById(R.id.btn_choose);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("/image/*");
//                startActivityForResult(intent, SELECT_PHOTO);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PHOTO);
            }
        });

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bitmap bitmap = ((BitmapDrawable) img_view.getDrawable()).getBitmap();

                AlertDialog.Builder builder = new AlertDialog.Builder(SelectActivity.this);
                builder.setTitle("Enter Name");

                final EditText editText = new EditText(SelectActivity.this);
                builder.setView(editText);

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!TextUtils.isEmpty(editText.getText().toString())){
                            dbHelper.addBitmap(editText.getText().toString(), Utils.getByte(bitmap));
                            Toast.makeText(getApplicationContext(), "Save Successfull", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Name cant be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null){
            Uri pickedImage = data.getData();
            img_view.setImageURI(pickedImage);
            btn_save.setEnabled(true);
        }
    }
}
