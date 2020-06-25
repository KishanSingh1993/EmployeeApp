package in.ktechnos.testapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_ID = "empID";
    public static final String COLUMN_FIRST_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_HIRE_DATE = "hiredata";
    public static final String COLUMN_MOBILE = "mobile";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_EMPLOYEES + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_EMAIL
            + " TEXT, " + COLUMN_MOBILE + " INTEGER " + ")";

    public EmployeeDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }
}
