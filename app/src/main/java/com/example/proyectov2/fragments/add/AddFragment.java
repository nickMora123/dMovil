package com.example.proyectov2.fragments.add;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectov2.R;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.viewmodels.UsuarioViewModel;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment {

    private PersonaViewModel vmPersona;
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
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        vmPersona = new ViewModelProvider(this).get(PersonaViewModel.class);

        nombreInput = view.findViewById(R.id.addname_input);
        apellidoInput = view.findViewById(R.id.addlastname_input);
        cedulaInput = view.findViewById(R.id.addcedula_input);
        telefonoInput = view.findViewById(R.id.addtelefono_input);
        emailInput = view.findViewById(R.id.addemail_input);

        view.findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText

                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String cedulaString = cedulaInput.getText().toString();
                String telefonoString = telefonoInput.getText().toString();


                if (!nombre.isEmpty() && !email.isEmpty() &&
                        !apellido.isEmpty() && !cedulaString.isEmpty() && !telefonoString.isEmpty()) {

                    int cedula = Integer.parseInt(cedulaString);
                    int telefono = Integer.parseInt(telefonoString);


                    vmPersona.insertarPersona(cedula, nombre, apellido, email, telefono);

                    Navigation.findNavController(v).navigate(R.id.action_addFragment_to_listFragment);
                    ejecutarServicio("http://192.168.137.78:80/my_database/insertarPersona.php");
                } else {

                    Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }

    public void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("cedula", cedulaInput.getText().toString());
                parametros.put("nombre", nombreInput.getText().toString());
                parametros.put("apellido", apellidoInput.getText().toString());
                parametros.put("email", emailInput.getText().toString());
                parametros.put("telefono", telefonoInput.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}