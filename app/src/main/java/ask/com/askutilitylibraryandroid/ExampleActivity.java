package ask.com.askutilitylibraryandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ExampleActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSIONS_REQUEST = 901;
    private static final int DOCUMENT_PICK_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * check single or multiple permissisions
         * */
       /* PermissionCheck permissionCheck = new PermissionCheck(this);
        permissionCheck.askPermissionIfMissing(STORAGE_PERMISSIONS_REQUEST,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION);*/

        /**
         * Alert Dialog Click Listeners
         * */
      /*  AlertDialogUtils alertDialogUtils = new AlertDialogUtils(this);
        alertDialogUtils.confirmationAlertDialog("Confirm", "Are u sure??");
        alertDialogUtils.setDialogInteractionListener(new AlertDialogUtils.OnDialogInteraction() {
            @Override
            public void onOkPressed() {
                Toast.makeText(ExampleActivity.this, "Ok presses", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelPressed() {

            }
        });*/

        /**
         * Document Picker Example
         * */
        /*documentPicker = new DocumentPicker(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documentPicker.startPicker(DOCUMENT_PICK_REQUEST);
            }
        });*/
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
//                String filePath = documentPicker.filePath(data);
//                FileUtils.openDoc(ExampleActivity.this, filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
