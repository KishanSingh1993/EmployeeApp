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
        hireDateEditText = findViewById(R.id.edit_text_hire_date);
        radioGroup = findViewById(R.id.radio_gender);
        femaleRadioButton = findViewById(R.id.radio_female);
        maleRadioButton = findViewById(R.id.radio_male);
        calendarImage = findViewById(R.id.image_view_hire_date);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_male) {
                    newEmployee.setGender("M");
                    if (mode.equals("Update")) {
                        oldEmployee.setGender("M");
                    }
                } else if (i == R.id.radio_female) {
                    newEmployee.setGender("F");
                    if (mode.equals("Update")) {
                        oldEmployee.setGender("F");
                    }
                }
            }
        });
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });
        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeData.open();
                if (mode != null) {
                    if (mode.equals("Add")) {
                        newEmployee.setFirstName(firstNameEditText.getText().toString());
                        newEmployee.setLastName(lastNameEditText.getText().toString());
                        newEmployee.setHireDate(hireDateEditText.getText().toString());
                        newEmployee.setDept(deptEditText.getText().toString());
                        if (maleRadioButton.isChecked())
                            newEmployee.setGender("M");
                        else
                            newEmployee.setGender("F");


                        employeeData.addEmployee(newEmployee);
                        employeeData.close();
                        Toast.makeText(AddUpdateEmployee.this, "Employee " + newEmployee.getFirstName() +
                                " is added successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddUpdateEmployee.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        oldEmployee.setFirstName(firstNameEditText.getText().toString());
                        oldEmployee.setLastName(lastNameEditText.getText().toString());
                        oldEmployee.setHireDate(hireDateEditText.getText().toString());
                        oldEmployee.setDept(deptEditText.getText().toString());
                        employeeData.updateEmployee(oldEmployee);
                        employeeData.close();
                        Toast.makeText(AddUpdateEmployee.this, "Employee " + oldEmployee.getFirstName() +
                                " is added successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddUpdateEmployee.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void initializeEmployee(long empId) {
        oldEmployee = employeeData.getEmployee(empId);
        if (oldEmployee != null) {
            firstNameEditText.setText(oldEmployee.getFirstName());
            lastNameEditText.setText(oldEmployee.getLastName());
            hireDateEditText.setText(oldEmployee.getHireDate());
            radioGroup.check(oldEmployee.getGender().equals("M") ? R.id.radio_male : R.id.radio_female);
            deptEditText.setText(oldEmployee.getDept());
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

        switch (view.getId()){}

    }
}
