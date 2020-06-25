package in.ktechnos.testapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import in.ktechnos.testapp.DB.EmployeeOperations;
import in.ktechnos.testapp.Fragments.DatePickerFragment;
import in.ktechnos.testapp.R;
import in.ktechnos.testapp.model.Employee;

public class AddUpdateEmployee extends AppCompatActivity implements DatePickerFragment.DateDialogListener, View.OnClickListener {

    private static final String EXTRA_EMP_ID = "in.ktechnos.testapp.sqlitepractice.empId";
    private static final String EXTRA_ADD_UPDATE = "in.ktechnos.testapp.sqlitepractice.add_update";
    private static final String DIALOG_DATE = "DialogDate";
    private ImageView calendarImage;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText deptEditText;
    private EditText hireDateEditText;
    private Button addUpdateButton;
    private Employee newEmployee;
    private Employee oldEmployee;
    private String mode;
    private long empId;
    private EmployeeOperations employeeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();

    }

    private void init(){

        newEmployee = new Employee();
        oldEmployee = new Employee();
        firstNameEditText = findViewById(R.id.edit_text_first_name);
        lastNameEditText = findViewById(R.id.edit_text_last_name);
        deptEditText = findViewById(R.id.edit_text_dept);
        addUpdateButton = findViewById(R.id.button_add_update_employee);
        employeeData = new EmployeeOperations(this);


        Log.d("test", "onCreate: " + getIntent().getStringExtra(EXTRA_ADD_UPDATE));
        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if (mode != null)
            if (mode.equals("Update")) {
                addUpdateButton.setText("Update Employee");
                empId = getIntent().getLongExtra(EXTRA_EMP_ID, 0);

                initializeEmployee(empId);
            }


        addUpdateButton.setOnClickListener(this);
    }

    private void addEmp(){

        Log.d("Name",firstNameEditText.getText().toString());

        if (!firstNameEditText.getText().toString().equals("") || !lastNameEditText.getText().toString().equals("") || !deptEditText.getText().toString().equals("")) {
            employeeData.open();

            newEmployee.setFirstName(firstNameEditText.getText().toString());
            newEmployee.setEmail(lastNameEditText.getText().toString());
            newEmployee.setMobile(deptEditText.getText().toString());

            employeeData.addEmployee(newEmployee);
            employeeData.close();
            Toast.makeText(AddUpdateEmployee.this, "Employee " + newEmployee.getFirstName() +
                    " is added successfully!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AddUpdateEmployee.this, MainActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();

        }
    }

    private void initializeEmployee(long empId) {
        oldEmployee = employeeData.getEmployee(empId);
        if (oldEmployee != null) {
            firstNameEditText.setText(oldEmployee.getFirstName());
            lastNameEditText.setText(oldEmployee.getEmail());
            deptEditText.setText(oldEmployee.getMobile());
        } else {
            Toast.makeText(this, "Employee not Present", Toast.LENGTH_SHORT).show();
        }
    }


    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    @Override
    public void onFinishDialog(Date date) {
        hireDateEditText.setText(formatDate(date));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button_add_update_employee:
                addEmp();
                break;
        }

    }
}
