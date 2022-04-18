package com.example.wristwearablecapstone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.wristwearablecapstone.databinding.ActivityMainBinding;
import com.example.wristwearablecapstone.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    EditText editTextIPAddress;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.settingsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).screen_return();
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);

                ((MainActivity)getActivity()).screen_return();
            }
        });

        editTextIPAddress = view.findViewById(R.id.editTextIPAddress);
        editTextIPAddress.setText(MainActivity.getStreamPath());
        editTextIPAddress.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s){
                MainActivity.setStreamPath(s.toString());

            }
        });

        // Populate resolution dropdown
        Spinner resDropdown = view.findViewById(R.id.res_dropdown);
        String[] resItems = {"4K", "1080p", "720p"};
        ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, resItems);
        resDropdown.setAdapter(resAdapter);
        resDropdown.setSelection(1);

        // populate framerate dropdown
        Spinner fpsDropdown = view.findViewById(R.id.fps_dropdown);
        String[] fpsItems = {"120", "100", "60", "50", "30", "25", "24"};
        ArrayAdapter<String> fpsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, fpsItems);
        fpsDropdown.setAdapter(fpsAdapter);
        fpsDropdown.setSelection(2);


        // populate bitrate dropdown
        Spinner bitDropdown = view.findViewById(R.id.bit_dropdown);
        String[] bitItems = {"8 Mbps", "4 Mbps", "2 Mbps"};
        ArrayAdapter<String> bitAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bitItems);
        bitDropdown.setAdapter(bitAdapter);
        bitDropdown.setSelection(0);
    }


}