package in.ktechnos.testapp.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import in.ktechnos.testapp.viewModel.LoginViewModel;
import in.ktechnos.testapp.viewModel.MainViewModel;

class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;


    public MainViewModelFactory(MainActivity mainActivity) {
        context = mainActivity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(context);
    }
}
