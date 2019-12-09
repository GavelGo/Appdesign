package Mobile.gavelgo.View.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import Mobile.gavelgo.View.Fragment.AlertFragment;
import Mobile.gavelgo.View.Fragment.ChatFragment;
import Mobile.gavelgo.View.Fragment.HomeFragment;
import Mobile.gavelgo.View.Fragment.SettingsFragment;
import Mobile.gavelgo.R;

public class MainActivity extends FragmentActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        selectedFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                selectedFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.nav_home:

                                selectedFragment = new HomeFragment();
                                break;

                            case R.id.nav_alert:
                                selectedFragment = new AlertFragment();
                                break;

                            case R.id.nav_chat:
                                selectedFragment = new ChatFragment();
                                break;

                            case R.id.nav_settings:
                                selectedFragment = new SettingsFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                selectedFragment).commit();
                        return true;
                    }
                });

    }
    /*private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.nav_alert:
                        selectedFragment = new AlertFragment();
                        break;
                    case R.id.nav_chat:
                        selectedFragment = new ChatFragment();
                        break;

                    case R.id.nav_settings:
                        selectedFragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        selectedFragment).commit();
                return true;
            }
        });
    }*/

   /* private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

  /*  private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){

                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_alert:
                            selectedFragment = new AlertFragment();
                            break;
                        case R.id.nav_chat:
                            selectedFragment = new ChatFragment();
                            break;

                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedFragment).commit();

                    return true;
                }

    };
*/

    @Override
    public void onBackPressed() {

        this.moveTaskToBack(true);
        // super.onBackPressed();
    }
}
