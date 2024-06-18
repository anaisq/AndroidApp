package com.unibuc.appointmentapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookingFragment extends Fragment {

    private AppointmentDAO appointmentDAO;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        appointmentDAO = new AppointmentDAO(getActivity());
        appointmentDAO.open();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_addAppointment);
        });

        FloatingActionButton fabDelete = view.findViewById(R.id.fab_delete);
        fabDelete.setOnClickListener(v -> {
            appointmentDAO.deleteAppointment();
            loadAppointments();
        });

        loadAppointments();

        return view;
    }

    private void loadAppointments() {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        adapter = new AppointmentAdapter(appointments);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onDestroy() {
        appointmentDAO.close();
        super.onDestroy();
    }
}
