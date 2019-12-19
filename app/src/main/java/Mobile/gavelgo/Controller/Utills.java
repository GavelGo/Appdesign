package Mobile.gavelgo.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tapadoo.alerter.Alerter;

import Mobile.gavelgo.R;

public class Utills {

    public static ProgressDialog progressDialog;

    public static void showDialog(Context context) {

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(context.getResources().getString(R.string.Pleasewait));

        progressDialog.setCanceledOnTouchOutside(false);

        if (progressDialog != null) {

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }
    public static void progressDialog_dismiss(Context context) {
        progressDialog.dismiss();
    }

    public static void showalerter(Context context, String message) {
        Alerter.create((Activity) context)
                .setTitle("Gavel go")
                .setText(message)
                .setBackgroundColorRes(R.color.colorAccent)
                .show();
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

}
