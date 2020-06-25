package in.ktechnos.testapp.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import in.ktechnos.testapp.model.Employee;

public class EmployeeOperations {

    public static final String TAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            EmployeeDBHandler.COLUMN_ID,
            EmployeeDBHandler.COLUMN_FIRST_NAME,
            EmployeeDBHandler.COLUMN_EMAIL,
            EmployeeDBHandler.COLUMN_MOBILE
    };

    public EmployeeOperations(Context context) {
        dbHandler = new EmployeeDBHandler(context);
    }

    public void open() {
        Log.i(TAG, "Database Opened ");
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database Closed ");
        dbHandler.close();
    }

    //inserting the employee
    public Employee addEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(EmployeeDBHandler.COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(EmployeeDBHandler.COLUMN_EMAIL, employee.getEmail());
        values.put(EmployeeDBHandler.COLUMN_MOBILE, employee.getMobile());

        long insertId = database.insert(EmployeeDBHandler.TABLE_EMPLOYEES, null, values);
        employee.setEmpId(insertId);
        return employee;
    }

    //getting the employee
    public Employee getEmployee(long id) {
        Employee e;
        open();
        Log.d(TAG, "getEmployee: " + String.valueOf(id));
        Cursor cursor = database.query(EmployeeDBHandler.TABLE_EMPLOYEES, allColumns,
                EmployeeDBHandler.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            Log.d(TAG, "getEmployee: " + cursor);
            e = new Employee(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));
        } catch (CursorIndexOutOfBoundsException a) {
            e = null;
        }
        close();
        return e;
    }

    //fetching all the employees
    public List<Employee> getAllEmployees() {
        open();
        Cursor cursor = database.query(EmployeeDBHandler.TABLE_EMPLOYEES, allColumns,
                null, null, null, null, null);
        List<Employee> employees = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Employee employee = new Employee();
                employee.setEmpId(cursor.getLong(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_ID)));
                employee.setFirstName(cursor.getString(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_FIRST_NAME)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_EMAIL)));
                employee.setMobile(cursor.getString(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_MOBILE)));
                employees.add(employee);
            }
        }
        close();
        return employees;
    }

    //Updating the Employee
    public int updateEmployee(Employee employee) {
        open();
        ContentValues values = new ContentValues();
        values.put(EmployeeDBHandler.COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(EmployeeDBHandler.COLUMN_EMAIL, employee.getEmail());
        values.put(EmployeeDBHandler.COLUMN_MOBILE, employee.getMobile());

        //Updating Row
        return database.update(EmployeeDBHandler.TABLE_EMPLOYEES, values,
                EmployeeDBHandler.COLUMN_ID + "=?", new String[]{String.valueOf(employee.getEmpId())});
    }

    //Deleting Employee
    public void removeEmployee(Employee employee) {
        open();
        database.delete(EmployeeDBHandler.TABLE_EMPLOYEES,
                EmployeeDBHandler.COLUMN_ID + "=" + employee.getEmpId(), null);
        close();
    }

}