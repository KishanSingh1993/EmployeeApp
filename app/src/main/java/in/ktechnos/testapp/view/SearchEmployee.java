package in.ktechnos.testapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.ktechnos.testapp.DB.EmployeeOperations;
import in.ktechnos.testapp.R;
import in.ktechnos.testapp.model.Employee;

public class SearchEmployee extends AppCompatActivity implements View.OnClickListener {

    private Employee oldEmployee;
    private EmployeeOperations employeeData;
    private TextView txtEmpDetails;
    private EditText etEmpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtEmpDetails = findViewById(R.id.details);
        etEmpId = findViewById(R.id.search);

        Button btnSearch = findViewById(R.id.btn_search_employee);
        btnSearch.setOnClickListener(this);

        oldEmployee = new Employee();
        employeeData = new EmployeeOperations(this);
    }

    @SuppressLint("SetTextI18n")
    private void initializeEmployee(long empId) {
        oldEmployee = employeeData.getEmployee(empId);
        if (oldEmployee != null) {
            txtEmpDetails.setText("Name: " + oldEmployee.getFirstName()+"\n" + "Email:"+oldEmployee.getEmail()+ "\n" + "Mobile: " +oldEmployee.getMobile());
        } else {
            Toast.makeText(this, "Employee not Present", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id==R.id.btn_search_employee){

            initializeEmployee(Long.parseLong(etEmpId.getText().toString()));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}