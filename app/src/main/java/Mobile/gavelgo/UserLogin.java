package Mobile.gavelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Email = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPwd);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 5");
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println(Email.getText().toString().equals("Admin"));
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });
    }
    private void validate(String email, String userPassword) {
        if (email.equals("Admin")&& userPassword.equals("1234")){
            Intent intent = new Intent(UserLogin.this, MainActivity.class);
            startActivity(intent);
            System.out.println(" Bill 1");
        }else{
            counter--;
            Info.setText("No of attempts remaining: " + String.valueOf(counter));
            System.out.println(" Bill 2");
            if(counter == 0){
                Login.setEnabled(false);
            }

        }
    }
}
