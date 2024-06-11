package com.example.proyectov2.fragments.add;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectov2.R;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.viewmodels.UsuarioViewModel;

public class RegisterFragment extends Fragment {
    private PersonaViewModel vmPersona;
    private UsuarioViewModel vmUsuario;
    EditText usernameInput;
    EditText passwordInput;
    EditText nombreInput;
    EditText apellidoInput;
    EditText cedulaInput;
    EditText telefonoInput;
    EditText emailInput;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        vmPersona = new ViewModelProvider(this).get(PersonaViewModel.class);
        vmUsuario = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usernameInput = view.findViewById(R.id.rusername_input);
        passwordInput = view.findViewById(R.id.rpassword_input);
        nombreInput = view.findViewById(R.id.name_input);
        apellidoInput = view.findViewById(R.id.lastname_input);
        cedulaInput = view.findViewById(R.id.cedula_input);
        telefonoInput = view.findViewById(R.id.telefono_input);
        emailInput = view.findViewById(R.id.email_input);

        view.findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String cedulaString = cedulaInput.getText().toString();
                String telefonoString = telefonoInput.getText().toString();


                if (!username.isEmpty() && !password.isEmpty() && !nombre.isEmpty() && !email.isEmpty() &&
                        !apellido.isEmpty() && !cedulaString.isEmpty() && !telefonoString.isEmpty()) {

                    int cedula = Integer.parseInt(cedulaString);
                    int telefono = Integer.parseInt(telefonoString);


                    vmPersona.insertarPersona(cedula, nombre, apellido, email, telefono);


                    int personaId = vmPersona.getLastInsertedId();


                    vmUsuario.insertarUsuario(personaId, username, password);


                    Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
                } else {

                    Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
