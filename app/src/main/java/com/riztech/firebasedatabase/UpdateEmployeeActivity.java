package com.riztech.firebasedatabase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.riztech.firebasedatabase.models.Employee;


public class UpdateEmployeeActivity extends AppCompatActivity {
    EditText edtName, edtAddress, edtPhoneNumber, edtSalary, edtDesignation;
    ProgressBar progress;

    Employee employee;

    String id ;

    // Declaration
    DatabaseReference mDatabaseReference;

    public static final String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        employee = getIntent().getParcelableExtra(DATA);
        id = employee.getId();

        edtAddress = findViewById(R.id.edtAddress);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtSalary = findViewById(R.id.edtSalary);
        edtDesignation = findViewById(R.id.edtDesignation);
        progress = findViewById(R.id.progress);

        edtAddress.setText(employee.getAddress());
        edtName.setText(employee.getName());
        edtPhoneNumber.setText(employee.getPhoneNumber());
        edtSalary.setText(String.valueOf(employee.getSalary()));
        edtDesignation.setText(employee.getDesignation());

        // Initialize
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("employees");
    }

    public void updateEmployee(View view) {

        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String salString = edtSalary.getText().toString().trim();
        String designation = edtDesignation.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phoneNumber)
                || TextUtils.isEmpty(salString) || TextUtils.isEmpty(designation)) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long salary = Long.parseLong(salString);

        Employee employee = new Employee(name, address, phoneNumber, salary, designation);

        employee.setId(id);

        progress.setVisibility(View.VISIBLE);

        // user
        mDatabaseReference.child(id).setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progress.setVisibility(View.GONE);
            }
        });

    }
}
