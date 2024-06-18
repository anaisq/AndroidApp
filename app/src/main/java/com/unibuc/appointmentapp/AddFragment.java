package com.unibuc.appointmentapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddFragment extends Fragment {
    EditText nameInput, phoneInput, dateInput;
    Button addButton;


    private AppointmentDAO appointmentDAO;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        nameInput = view.findViewById(R.id.name_input);
        phoneInput = view.findViewById(R.id.phone_input);
        dateInput = view.findViewById(R.id.date_input);
        addButton = view.findViewById(R.id.add_button);

        addButton.setOnClickListener(v -> {
            DatabaseHelper myDB = new DatabaseHelper(view.getContext());
            myDB.addAppointment(nameInput.getText().toString().trim(), phoneInput.getText().toString().trim(), dateInput.getText().toString().trim());
        });

        // Handle back press
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Define your custom back press handling logic
                Navigation.findNavController(view).navigateUp();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

            return view;
    }
}