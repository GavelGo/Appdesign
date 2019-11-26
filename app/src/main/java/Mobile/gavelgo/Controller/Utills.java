package Mobile.gavelgo.Controller;

import android.app.Activity;
import android.content.Context;

import com.tapadoo.alerter.Alerter;

import Mobile.gavelgo.R;

public class Utills {


    public static void showalerter(Context context, String message) {
        Alerter.create((Activity) context)
                .setTitle("Gavel go")
                .setText(message)
                .setBackgroundColorRes(R.color.colorAccent)
                .show();
    }

}
