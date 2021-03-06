package com.example.trabajopractico1_login.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopractico1_login.models.Usuario;
import com.example.trabajopractico1_login.request.ApiClient;
import com.example.trabajopractico1_login.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> error;
    private ApiClient apiClient;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        apiClient=new ApiClient();
    }


    public LiveData<String> getError(){
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void autenticar(String mail, String pass){
        Usuario usuario=apiClient.login(context,mail,pass);
        if (usuario != null){
            error.setValue("");
            Intent intent=new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario",usuario);
            context.startActivity(intent);
        }
        else {
            error.setValue("Email o Password incorrecto ");
        }
    }

    public void Registrar(){
        Intent intent=new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
