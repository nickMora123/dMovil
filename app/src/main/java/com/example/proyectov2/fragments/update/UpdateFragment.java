package com.example.proyectov2.fragments.update;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

public class UpdateFragment extends Fragment {

    private PersonaViewModel vmPersona;
    EditText idInput;
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
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        vmPersona = new ViewModelProvider(this).get(PersonaViewModel.class);
        idInput = view.findViewById(R.id.id_input);
        nombreInput = view.findViewById(R.id.upname_input);
        apellidoInput = view.findViewById(R.id.uplastname_input);
        cedulaInput = view.findViewById(R.id.upcedula_input);
        telefonoInput = view.findViewById(R.id.uptelefono_input);
        emailInput = view.findViewById(R.id.upemail_input);

        view.findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText
                String idString = idInput.getText().toString();
                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String cedulaString = cedulaInput.getText().toString();
                String telefonoString = telefonoInput.getText().toString();


                if (!nombre.isEmpty() && !email.isEmpty() &&
                        !apellido.isEmpty() && !cedulaString.isEmpty() && !telefonoString.isEmpty()) {

                    int cedula = Integer.parseInt(cedulaString);
                    int telefono = Integer.parseInt(telefonoString);
                    int personaId = Integer.parseInt(idString);

                    vmPersona.updatePersona(personaId, cedula, nombre, apellido, email, telefono);




                    Navigation.findNavController(v).navigate(R.id.action_updateFragment_to_listFragment);
                } else {

                    Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}