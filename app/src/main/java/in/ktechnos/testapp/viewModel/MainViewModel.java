package in.ktechnos.testapp.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import in.ktechnos.testapp.view.AddUpdateEmployee;

public class MainViewModel extends ViewModel {

    private Context context;

    public MainViewModel(Context context) {
        this.context = context;

    }

    public void goToSearch(){

    }

    public void goToAdd(){

        context.startActivity(new Intent(context, AddUpdateEmployee.class));
    }
}
