package in.ktechnos.testapp.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import in.ktechnos.testapp.view.LoginActivity;
import in.ktechnos.testapp.viewModel.LoginViewModel;

class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;



    public LoginViewModelFactory(LoginActivity loginActivity)
    {
        context= loginActivity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(context);
    }
}