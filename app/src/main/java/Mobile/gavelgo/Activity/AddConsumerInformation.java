package Mobile.gavelgo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.R;

public class AddConsumerInformation extends Activity implements View.OnClickListener {

    EditText firstnameET,lastnameET,phoneET,streetET,aptET,cityET,stateET,zipET,descriptionET;
    Button saveBT,cancelBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addconsumer_information);

        saveBT=(Button)findViewById(R.id.saveBT);
        cancelBT=(Button)findViewById(R.id.cancelBT);
        firstnameET=(EditText) findViewById(R.id.firstnameET);
        lastnameET=(EditText) findViewById(R.id.lastnameET);
        phoneET=(EditText) findViewById(R.id.phoneET);
        streetET=(EditText) findViewById(R.id.streetET);
        aptET=(EditText) findViewById(R.id.aptET);
        cityET=(EditText) findViewById(R.id.cityET);
        stateET=(EditText) findViewById(R.id.stateET);
        zipET=(EditText) findViewById(R.id.zipET);
        descriptionET=(EditText) findViewById(R.id.descriptionET);

        saveBT.setOnClickListener(this);
        cancelBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.saveBT:


                if (firstnameET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter first name");
                } else if (lastnameET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter last name");
                } else if (phoneET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter phone number");
                } else if (streetET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter street address");
                } else if (aptET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter apt/suite");
                } else if (cityET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter city");
                } else if (stateET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter state");
                } else if (zipET.getText().toString().equals("")) {
                    Utills.showalerter(AddConsumerInformation.this, "Please enter zipcode");
                } else {
                    Utills.showalerter(AddConsumerInformation.this, "Profile save successfully");

                    Intent intent=new Intent(AddConsumerInformation.this,MainActivity.class);
                    startActivity(intent);

                }

                break;

            case R.id.cancelBT:

                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
