package Mobile.gavelgo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddProductActivity extends Activity implements View.OnClickListener {
    EditText productnameET,descriptionET;
    Spinner categorySP,subcategorySP;
    CircleImageView profileIV;
    Button browseBT,saveproductBT,cancelBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        productnameET=(EditText)findViewById(R.id.productnameET);
        descriptionET=(EditText)findViewById(R.id.descriptionET);
        categorySP=(Spinner) findViewById(R.id.categorySP);
        subcategorySP=(Spinner) findViewById(R.id.subcategorySP);
        profileIV=(CircleImageView) findViewById(R.id.profileIV);
        browseBT=(Button) findViewById(R.id.browseBT);
        saveproductBT=(Button) findViewById(R.id.saveproductBT);
        cancelBT=(Button) findViewById(R.id.cancelBT);

        browseBT.setOnClickListener(this);
        saveproductBT.setOnClickListener(this);
        cancelBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.saveproductBT:

                if (productnameET.getText().toString().equals("")){

                    Utills.showalerter(AddProductActivity.this,"Please enter product name");
                }

                else if (categorySP.getSelectedItem().equals("Select Business Category")){

                    Utills.showalerter(AddProductActivity.this,"Please select businnes category");

                }  else if (subcategorySP.getSelectedItem().equals("Select Subcategory")){

                    Utills.showalerter(AddProductActivity.this,"Please select sub category");
                }
                else{
                    Utills.showalerter(AddProductActivity.this,"Product save successfully");
                }

                break;

            case R.id.cancelBT:

                finish();

                break;
        }
    }
}
