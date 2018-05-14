package ask.com.asklibrary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by flatmind on 4/5/18.
 */

public class AlertDialogUtils {

    OnDialogInteraction dialogInteraction;
    Context context;

    public AlertDialogUtils(Context context) {
        this.context = context;
    }

    public interface OnDialogInteraction {
        void onOkPressed();

        void onCancelPressed();
    }

    public void setDialogInteractionListener(OnDialogInteraction listener) {
        this.dialogInteraction = listener;
    }

    public void simpleAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AskDefaultDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void confirmationAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AskDefaultDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                try {
                    dialogInteraction.onOkPressed();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("exception", "onClick: Activity must Implement DialogInteraction Interface");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInteraction.onCancelPressed();
            }
        });
        builder.show();
    }

    public void simpleAlertDialogFinishActivity(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AskDefaultDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ((Activity) context).setResult(Activity.RESULT_OK);
                ((Activity) context).finish();
            }
        });
        builder.show();
    }

    public void simpleAlertDialog(String title, String message, int style) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, style);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
