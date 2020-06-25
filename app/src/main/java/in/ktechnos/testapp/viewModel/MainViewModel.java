package in.ktechnos.testapp.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import in.ktechnos.testapp.view.AddUpdateEmployee;
import in.ktechnos.testapp.view.SearchEmployee;
import in.ktechnos.testapp.view.ViewAllEmployees;

public class MainViewModel extends ViewModel {

    private Context context;

    public MainViewModel(Context context) {
        this.context = context;

    }

    public void viewList(){

        context.startActivity(new Intent(context, ViewAllEmployees.class));
    }

    public void goToSearch(){
        context.startActivity(new Intent(context, SearchEmployee.class));
    }

    public void goToAdd(){

        context.startActivity(new Intent(context, AddUpdateEmployee.class));
    }
}
