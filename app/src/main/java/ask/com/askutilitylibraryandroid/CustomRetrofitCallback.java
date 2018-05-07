package ask.com.askutilitylibraryandroid;

import android.app.ProgressDialog;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flatmind on 19/8/17.
 */

public class CustomRetrofitCallback<T> implements Callback<T> {
    private ProgressDialog progress = null;

    public CustomRetrofitCallback(Context context) {
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.show();
    }

    public CustomRetrofitCallback(Context context, boolean showProgressDialog) {
        if (showProgressDialog) {
            progress = new ProgressDialog(context);
            progress.setCancelable(false);
            progress.show();
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }
}
