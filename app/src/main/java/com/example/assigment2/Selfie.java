package com.example.assigment2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Selfie extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_INTENT_CODE = 101;
    private ImageView imageView;
    Button previousButton,submitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selfie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousButton = findViewById(R.id.Agebutton);
        submitButton = findViewById(R.id.submitButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selfie.this, Age.class);
                startActivity(intent);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Selfie.this, "Submit button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        imageView = findViewById(R.id.imageView);
        Button buttonTakeSelfie = findViewById(R.id.buttonTakeSelfie);
        previousButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_arrow_back_ios_24
                , 0, 0, 0);
        buttonTakeSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if camera permission is granted
                if (ContextCompat.checkSelfPermission(Selfie.this, android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(Selfie.this,
                            new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                } else {
                    // Open camera to take a picture
                    openCamera();
                }
            }
        });


    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_INTENT_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_INTENT_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap); // Display the selfie in the ImageView
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission required to take a selfie", Toast.LENGTH_SHORT).show();
            }
        }
    }

}