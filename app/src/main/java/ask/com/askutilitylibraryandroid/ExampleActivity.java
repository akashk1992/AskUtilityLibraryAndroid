package ask.com.askutilitylibraryandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ask.com.asklibrary.DocumentPicker;
import ask.com.asklibrary.FileUtils;
import ask.com.asklibrary.PermissionCheck;

public class ExampleActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSIONS_REQUEST = 901;
    private static final int DOCUMENT_PICK_REQUEST = 101;
    private DocumentPicker documentPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.pick);
        documentPicker = new DocumentPicker(this);

        /**
         * Permission checker for single or multiple
         * */
        PermissionCheck permissionCheck = new PermissionCheck(this);
        permissionCheck.askPermissionIfMissing(STORAGE_PERMISSIONS_REQUEST, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Document picker from file explorer for local as well as Google Drive
                 * */
                documentPicker.startPicker(DOCUMENT_PICK_REQUEST);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_PERMISSIONS_REQUEST:
                if ((grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == DOCUMENT_PICK_REQUEST) {
            try {
                String filePath = documentPicker.filePath(data);
                FileUtils.openDoc(ExampleActivity.this, filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
