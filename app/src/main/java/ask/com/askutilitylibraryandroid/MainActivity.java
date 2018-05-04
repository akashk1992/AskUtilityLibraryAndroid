package ask.com.askutilitylibraryandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ask.com.mylibrary.AlertDialogUtils;
import ask.com.mylibrary.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test", "onCreate: " + NetworkUtils.isNetworkAvailable(this));
        AlertDialogUtils.SimpleAlertDialog(this, "Title asdnaksd", "message jndfk asd");
    }
}
