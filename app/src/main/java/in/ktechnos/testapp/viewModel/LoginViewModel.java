package in.ktechnos.testapp.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import in.ktechnos.testapp.view.MainActivity;

public class LoginViewModel extends ViewModel {

    private static final String TAG= LoginViewModel.class.getSimpleName();
    public MutableLiveData<String> email= new MutableLiveData<>();
    public MutableLiveData<String> password= new MutableLiveData<>();
    private Context context;
    private ProgressDialog progressDialog;
    private String userName = "admin";
    private String userPass = "admin";


    public LoginViewModel(Context context) {
        this.context = context;
        progressDialog= new ProgressDialog(context);
        progressDialog.setMessage("Please wait ...");
    }


    public  void loginButton()
    {

        if (email.getValue().equals(userName)&&password.getValue().equals(userPass)){

            context.startActivity(new Intent(context, MainActivity.class));
        }
        else {

            Toast.makeText(context,"Username or Password wrong !",Toast.LENGTH_LONG).show();
        }

    }

}