package ezh.kitdelprogramador.fragmentos;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.LinkedList;

import ezh.kitdelprogramador.R;
import ezh.kitdelprogramador.automatas.AutomataDigito;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConvertidor extends Fragment {


    public FragmentConvertidor() {
        // Required empty public constructor
    }

    EditText txtNumero1;
    Spinner spinner_base_num1;
    Spinner spinner_base_result;
    Button button_convertir;
    TextView textView_result;
    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_convertidor, container, false);

        setHasOptionsMenu(true);
        // Mapear items de los fragments
        txtNumero1 = (EditText) rootView.findViewById(R.id.text_convert_num1);
        spinner_base_num1 = (Spinner) rootView.findViewById(R.id.spinner_convert_base_num1);
        spinner_base_result = (Spinner) rootView.findViewById(R.id.spinner_convert_base_resultado);
        button_convertir = (Button) rootView.findViewById(R.id.btnConvertir);

        textView_result = (TextView)rootView.findViewById(R.id.text_resultado2);



        button_convertir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Obtenemos el número
                String numero = txtNumero1.getText().toString();
                // Validamos que no este vacio
                if (numero.equals(""))
                {
                    Toast.makeText(getContext(), "Escribe un número", Toast.LENGTH_LONG).show();
                } else
                {
                    textView_result.setText("");
                    String base_origen = spinner_base_num1.getSelectedItem().toString();
                    String base_final = spinner_base_result.getSelectedItem().toString();

                    if (validar_cadena(numero,Integer.parseInt(base_origen)))
                    {

                        textView_result.setText(division_sucesivas(base_10(numero, base_origen), base_final));
                        // guardar
                    }
                    else
                    {
                        Toast.makeText(getContext(),"El número: "+numero+" no está escrito en la base: "+base_origen,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_principal,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_borrar:
                textView_result.setText("");
                txtNumero1.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean validar_cadena(String numero,int base_origen)
    {
        int estado = 1;
        AutomataDigito ad = new AutomataDigito(estado,base_origen);
        // convertir el string numero a un arreglo de caracteres
        char arreglo_numero[] = numero.toCharArray();

        for (char simbolo : arreglo_numero)
        {
            estado = ad.transicion(simbolo);
            Log.e("EZH","** Nuevo Estado ** -> "+estado);
            if (estado < 0)
            {
                break;
            }
            ad.set_estado(estado);
        }
        if (estado == 2 || estado == 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // devuelve el numero en base_final
    public String division_sucesivas(String numero_base10, String base_final) {
        int numero_base_final = Integer.parseInt(base_final);
        int numero_base_10 = Integer.parseInt(numero_base10);

        int dividendo = 1;
        int residuo = 0;
        String r = "";
        ArrayList<Integer> array_aux = new ArrayList<Integer>();
        Log.e("EZH", "***************** DIVISION SUCESIVA **********************");
        while (numero_base_10 != 0) {
            dividendo = (numero_base_10 / numero_base_final);
            Log.e("EZH", "Dividendo = " + dividendo);
            residuo = (numero_base_10 % numero_base_final);
            Log.e("EZH", "Residuo = " + residuo);
            array_aux.add(residuo);
            numero_base_10 = dividendo;
            Log.e("EZH", "Nuevo Numero = " + numero_base_10);
        }

        r = invertir_resultado(array_aux);
        return r;
    }

    public String invertir_resultado(ArrayList<Integer> lista)
    {
        String resultado = "";
        if (lista.size() > 0)
        {
            LinkedList<String> auxiliar = new LinkedList<>();

            for (int numero : lista) {
                auxiliar.push(get_int_to_String(numero));
            }

            for (String s : auxiliar) {
                resultado += s;
            }
        }
        else
        {
            resultado = "0";

        }
        return resultado;
    }

    public String get_int_to_String(int numero) {
        if (numero == 10) {
            return "A";
        } else if (numero == 11) {
            return "B";
        } else if (numero == 12) {
            return "C";
        } else if (numero == 13) {
            return "D";
        } else if (numero == 14) {
            return "E";
        } else if (numero == 15) {
            return "F";
        } else {
            return "" + numero;
        }
    }

    // devuelve el numero en base10
    public String base_10(String numero, String base_origen) {

        int numero_base = Integer.parseInt(base_origen);
        int suma = 0;
        // convertir el string numero a un arreglo de numeros
        char arreglo_numero[] = numero.toCharArray();

        ArrayList<Integer> arrayAux = new ArrayList<Integer>(arreglo_numero.length);
        // convertir cada caracter a numero
        for (char c : arreglo_numero) {
            Log.e("EZH", "Caracter " + c);
            arrayAux.add(get_int_of_letter(c));
        }
        // metodo de ruffini
        for (int i = 0; i < arrayAux.size(); i++) {
            suma += arrayAux.get(i);
            Log.e("EZH", "-> " + suma);
            if (i != (arrayAux.size() - 1)) {
                suma = (numero_base * suma);
                Log.e("EZH", "-> " + suma);
            }
        }
        Log.e("EZH", "SUMA: " + suma);
        return "" + suma;
    }

    public int get_int_of_letter(char letra) {
        String numero_aux = "";
        if (letra == 'A') {
            return 10;
        } else if (letra == 'B') {
            return 11;
        } else if (letra == 'C') {
            return 12;
        } else if (letra == 'D') {
            return 13;
        } else if (letra == 'E') {
            return 14;
        } else if (letra == 'F') {
            return 15;
        } else {
            numero_aux = String.valueOf(letra);
            return Integer.parseInt(numero_aux);
        }

    }
}
