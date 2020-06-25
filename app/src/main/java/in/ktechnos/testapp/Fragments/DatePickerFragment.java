package in.ktechnos.testapp.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.GregorianCalendar;

import in.ktechnos.testapp.R;

public class DatePickerFragment extends DialogFragment {
    private DatePicker datePicker;
    private static final int REQUEST_DATE =1;
    public static final String EXTRA_DATE="in.ktechnos.testapp.sqlitepractice.date";

    public interface DateDialogListener{
        void onFinishDialog(Date date);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v= LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date,null);
        datePicker=(DatePicker)v.findViewById(R.id.dialog_date_date_picker);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int year=datePicker.getYear();
                                int month=datePicker.getMonth();
                                int day=datePicker.getDayOfMonth();
                                Date date=new GregorianCalendar(year,month,day).getTime();
                                DateDialogListener activity=(DateDialogListener)getActivity();
                                activity.onFinishDialog(date);
                                dismiss();
                            }
                        })
                .create();
    }
}
