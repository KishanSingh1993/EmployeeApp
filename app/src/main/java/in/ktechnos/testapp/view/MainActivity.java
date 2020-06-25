package in.ktechnos.testapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.WindowManager;

import in.ktechnos.testapp.R;
import in.ktechnos.testapp.databinding.ActivityMainBinding;
import in.ktechnos.testapp.viewModel.LoginViewModel;
import in.ktechnos.testapp.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
    }

    private void init(){

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(this)).get(MainViewModel.class);
        activityMainBinding.setMainviewModel(mainViewModel);
        activityMainBinding.executePendingBindings();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}