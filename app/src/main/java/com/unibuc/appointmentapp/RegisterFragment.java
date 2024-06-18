package com.unibuc.appointmentapp;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.navigation.Navigation;
import com.google.firebase.auth.FirebaseAuth;



public class RegisterFragment extends Fragment {
    private EditText registerEmailEditText, registerPasswordEditText;
    private Button registerConfirmButton;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        registerEmailEditText = view.findViewById(R.id.registerEmailEditText);
        registerPasswordEditText = view.findViewById(R.id.registerPasswordEditText);
        registerConfirmButton = view.findViewById(R.id.registerConfirmButton);

        mAuth = FirebaseAuth.getInstance();

        registerConfirmButton.setOnClickListener(v -> registerUser());

        return view;
    }

    private void registerUser() {
        String email = registerEmailEditText.getText().toString().trim();
        String password = registerPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Registration success
                        Toast.makeText(getActivity(), "Registration Successful.", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                    } else {
                        // If registration fails, display a message to the user.
                        Toast.makeText(getActivity(), "Registration Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
