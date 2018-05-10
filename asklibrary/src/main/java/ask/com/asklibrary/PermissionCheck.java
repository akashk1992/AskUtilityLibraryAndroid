package ask.com.asklibrary;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by flatmind on 9/5/18.
 */

public class PermissionCheck {
    private Context context;

    public PermissionCheck(Context context) {
        this.context = context;
    }

    public boolean askPermissionIfMissing(int requestCode, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        permissions,
                        requestCode);
                break;
            }
        }
        return true;
    }

    public boolean isGranted(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void askPermission(int requestCode, String permission) {
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{permission},
                requestCode);
    }
}
