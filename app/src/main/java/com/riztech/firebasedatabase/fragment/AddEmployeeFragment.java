package com.riztech.firebasedatabase.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.riztech.firebasedatabase.R;
import com.riztech.firebasedatabase.models.Employee;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEmployeeFragment extends Fragment {

    EditText edtName, edtAddress, edtPhoneNumber, edtSalary, edtDesignation;
    ProgressBar progress;

    Button btnAdd;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);

        edtAddress = view.findViewById(R.id.edtAddress);
        edtName =  view.findViewById(R.id.edtName);
        edtPhoneNumber =  view.findViewById(R.id.edtPhoneNumber);
        edtSalary =  view.findViewById(R.id.edtSalary);
        edtDesignation =  view.findViewById(R.id.edtDesignation);
        progress =  view.findViewById(R.id.progress);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToDatabase();

            }
        });

        return view ;
    }

    private void addToDatabase() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String salString = edtSalary.getText().toString().trim();
        String designation = edtDesignation.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phoneNumber)
                || TextUtils.isEmpty(salString) || TextUtils.isEmpty(designation)) {
            Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long salary = Long.parseLong(salString);

        Employee employee = new Employee(name, address, phoneNumber, salary, designation);

    }

}
