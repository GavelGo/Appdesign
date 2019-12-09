package Mobile.gavelgo.View.Activity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.R;

public class UserLogin extends Activity implements View.OnClickListener {
    private EditText Email;
    private EditText Password;
    private ImageView hidepassIV;
    private TextView Info, signupTV, forgotTV;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initview();


    }

    private void initview() {

        Email = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPwd);
        // Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        hidepassIV = (ImageView) findViewById(R.id.hidepassIV);
        signupTV = (TextView) findViewById(R.id.signupTV);
        forgotTV = (TextView) findViewById(R.id.forgotTV);

        Login.setOnClickListener(this);
        hidepassIV.setOnClickListener(this);
        signupTV.setOnClickListener(this);
        forgotTV.setOnClickListener(this);


        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validate(Email.getText().toString(), Password.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.btnLogin:

                validate(Email.getText().toString(), Password.getText().toString());

                break;

            case R.id.hidepassIV:

                if ("work".equals(hidepassIV.getTag())) {
                    hidepassIV.setBackground(getResources().getDrawable(R.drawable.hide_password));
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Password.setSelection(Password.length());
                    hidepassIV.setTag("not work");

                } else {
                    hidepassIV.setBackground(getResources().getDrawable(R.drawable.password_visible));
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Password.setSelection(Password.length());
                    hidepassIV.setTag("work");
                }

                break;

            case R.id.signupTV:

                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);

                break;

            case R.id.forgotTV:

                forgotpass_dialog();

                break;
        }

    }

    private void forgotpass_dialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        dialog.setContentView(R.layout.forgotpass_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button cancelBT = (Button) dialog.findViewById(R.id.cancelBT);
        RelativeLayout crossRL = (RelativeLayout) dialog.findViewById(R.id.crossRL);
        final EditText usernameET = (EditText) dialog.findViewById(R.id.usernameET);
        Button resetBT = (Button) dialog.findViewById(R.id.resetBT);
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        crossRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        resetBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameET.getText().toString().equals("")) {

                    Utills.showalerter(UserLogin.this, "Please enter your username");
                } else {
                    dialog.dismiss();

                    Intent intent = new Intent(UserLogin.this, ResetPassordActivity.class);
                    intent.putExtra("username",usernameET.getText().toString());
                    startActivity(intent);
                }

            }
        });

        dialog.show();
    }

    private void validate(String email, String userPassword) {

        if (email.equals("")) {

            Utills.showalerter(UserLogin.this, "Please enter email");

        } else if (isEmailValid(email) == false) {

            Utills.showalerter(UserLogin.this, "Please enter valid email");

        } else if (userPassword.equals("")) {

            Utills.showalerter(UserLogin.this, "Please enter password");
        }
        else {
            Intent intent = new Intent(UserLogin.this, MainActivity.class);
            startActivity(intent);

        }
    }

    public Boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
