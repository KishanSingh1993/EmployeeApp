package in.ktechnos.testapp.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.List;

import in.ktechnos.testapp.DB.EmployeeOperations;
import in.ktechnos.testapp.R;
import in.ktechnos.testapp.model.Employee;

public class ViewAllEmployees extends ListActivity {


    private EmployeeOperations employeeOperations;
    List<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employees);

        employeeOperations=new EmployeeOperations(this);
        employeeOperations.open();
        employees=employeeOperations.getAllEmployees();
        employeeOperations.close();
        if(!employees.isEmpty()) {
            ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, employees);
            setListAdapter(adapter);
        }
        else
            Toast.makeText(this, "No Data Present", Toast.LENGTH_SHORT).show();
    }
}
