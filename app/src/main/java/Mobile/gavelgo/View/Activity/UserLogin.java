package Mobile.gavelgo.View.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import Mobile.gavelgo.Controller.Apis.AuthApi;
import Mobile.gavelgo.Controller.Apis.LoginApi;
import Mobile.gavelgo.Controller.Apis.UserRegisterApi;
import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.Model.AuthResponse;
import Mobile.gavelgo.Model.LoginResponse;
import Mobile.gavelgo.Model.RegisterUserResponse;
import Mobile.gavelgo.R;

public class UserLogin extends Activity implements View.OnClickListener {
    private EditText emailET,passET;
    private ImageView hidepassIV;
    private TextView Info, signupTV, forgotTV;
    private Button loginBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initview();


    }

    private void initview() {

        emailET = (EditText) findViewById(R.id.emailET);
        passET = (EditText) findViewById(R.id.passET);
        // Info = (TextView) findViewById(R.id.tvInfo);
        loginBT = (Button) findViewById(R.id.loginBT);
        hidepassIV = (ImageView) findViewById(R.id.hidepassIV);
        signupTV = (TextView) findViewById(R.id.signupTV);
        forgotTV = (TextView) findViewById(R.id.forgotTV);

        loginBT.setOnClickListener(this);
        hidepassIV.setOnClickListener(this);
        signupTV.setOnClickListener(this);
        forgotTV.setOnClickListener(this);


        passET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validate(emailET.getText().toString(), passET.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.loginBT:

                validate(emailET.getText().toString(), passET.getText().toString());

                break;

            case R.id.hidepassIV:

                if ("work".equals(hidepassIV.getTag())) {
                    hidepassIV.setBackground(getResources().getDrawable(R.drawable.hide_password));
                    passET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passET.setSelection(passET.length());
                    hidepassIV.setTag("not work");

                } else {
                    hidepassIV.setBackground(getResources().getDrawable(R.drawable.password_visible));
                    passET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passET.setSelection(passET.length());
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

        } /*else if (isEmailValid(email) == false) {

            Utills.showalerter(UserLogin.this, "Please enter valid email");

        }*/ else if (userPassword.equals("")) {

            Utills.showalerter(UserLogin.this, "Please enter password");
        }
        else {
            authApis();
        }
    }

    private void authApis() {
        AuthApi authApi = new AuthApi();
        if (Utills.isConnectingToInternet(UserLogin.this)) {
            try {
                Utills.showDialog(UserLogin.this);
                authApi.authapi_const(UserLogin.this,emailET.getText().toString(), passET.getText().toString(), new AuthApi.Auth_CallBack() {


                    @Override
                    public void onSuccess(@Nullable AuthResponse body) {

                        Log.d("tag","Api "+ "success");


                        String token=body.getToken();

                        //loginApi("Bearer "+token);

                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override

                    public void onFailure(@NotNull String body) {

                        Utills.progressDialog_dismiss(UserLogin.this);
                        Log.d("tag","Api"+ "faliure msg="+body);


                    }


                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utills.showalerter(this, "Please check your internet connection");
        }


    }

    private void loginApi(String token) {


        LoginApi loginapi = new LoginApi();

        if (Utills.isConnectingToInternet(UserLogin.this)) {
            try {

                loginapi.login_const(UserLogin.this,token,emailET.getText().toString(), passET.getText().toString(), new LoginApi.Login_CallBack() {


                    @Override
                    public void onSuccess(@Nullable LoginResponse body) {

                        Log.d("tag","LoginApi "+ "success");
                        Utills.progressDialog_dismiss(UserLogin.this);


                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override

                    public void onFailure(@NotNull String body) {

                        Utills.progressDialog_dismiss(UserLogin.this);
                        Log.d("tag","LoginApi"+ "faliure msg="+body);


                    }


                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utills.showalerter(this, "Please check your internet connection");
        }

    }

    public Boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
