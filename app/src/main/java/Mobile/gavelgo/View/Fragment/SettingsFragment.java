package Mobile.gavelgo.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Mobile.gavelgo.R;


public class SettingsFragment extends Fragment {
//    private HomeViewModel homeViewModel;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){


        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

}
