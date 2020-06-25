package in.ktechnos.testapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.WindowManager;

import in.ktechnos.testapp.R;
import in.ktechnos.testapp.databinding.ActivityLoginBinding;
import in.ktechnos.testapp.viewModel.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG= LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
    }

    private void init()
    {

        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        LoginViewModel loginViewmodel = ViewModelProviders.of(this, new LoginViewModelFactory(this)).get(LoginViewModel.class);
        loginBinding.setLoginviewModel(loginViewmodel);
        loginBinding.executePendingBindings();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}