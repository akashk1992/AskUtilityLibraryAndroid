package ask.com.asklibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;

/**
 * Created by flatmind on 7/5/18.
 */

public class AskUtility {
    private Context mContext;

    public AskUtility(Context mContext) {
        this.mContext = mContext;
    }

    public int dpToPx(int dp) {
        Resources r = mContext.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public String getMimeTypeForUri(Uri uri) {
        String mimeType;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = mContext.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        }
        return mimeType;
    }

    /**
     * Hide the Soft Keyboard.
     */
    public void hideSoftKeyboard() {
        Activity activity = (Activity) mContext;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null)
            return;
        if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void openPhoneDialerWithNumber(String number) throws ActivityNotFoundException {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        if (TextUtils.isEmpty(number))
            return;
        callIntent.setData(Uri.parse("tel:" + number));
        mContext.startActivity(callIntent);
    }

    public void sendEmailWithGmail(String email) throws ActivityNotFoundException {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.setType("message/rfc822");
        mContext.startActivity(emailIntent);
    }
}
