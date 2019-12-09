package Mobile.gavelgo.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import Mobile.gavelgo.R;

public class ResetPassordActivity   extends Activity implements View.OnClickListener {
    EditText usernameET,passET,conpassET;
    ImageView passIV,conpassIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        usernameET=(EditText)findViewById(R.id.usernameET);
        passET=(EditText)findViewById(R.id.passET);
        conpassET=(EditText)findViewById(R.id.conpassET);
        passIV=(ImageView) findViewById(R.id.passIV);
        conpassIV=(ImageView) findViewById(R.id.conpassIV);

        passIV.setOnClickListener(this);
        conpassIV.setOnClickListener(this);

        if (getIntent().getStringExtra("username")!=null){
            usernameET.setText(getIntent().getStringExtra("username"));

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.passIV:

                if ("work".equals(passIV.getTag())) {
                    passIV.setBackground(getResources().getDrawable(R.drawable.hide_password));
                    passET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passET.setSelection(passET.length());
                    passIV.setTag("not work");

                } else {
                    passIV.setBackground(getResources().getDrawable(R.drawable.password_visible));
                    passET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passET.setSelection(passET.length());
                    passIV.setTag("work");
                }

                break;

            case R.id.conpassIV:

                if ("work".equals(conpassIV.getTag())) {
                    conpassIV.setBackground(getResources().getDrawable(R.drawable.hide_password));
                    conpassET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    conpassET.setSelection(conpassET.length());
                    conpassIV.setTag("not work");

                } else {
                    conpassIV.setBackground(getResources().getDrawable(R.drawable.password_visible));
                    conpassET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    conpassET.setSelection(conpassET.length());
                    conpassIV.setTag("work");
                }

                break;
        }

    }
}
