package ezh.kitdelprogramador.fragmentos;


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
public class FragmentCalculadora extends Fragment
{
    // Constructor vacio
    public FragmentCalculadora()
    {
        // Required empty public constructor
    }
    // Declaración de variables globales
    EditText text_num1;
    Spinner spinner_base_num1;
    Spinner spinner_operadores;
    EditText text_num2;
    Spinner spinner_base_num2;
    Spinner spinner_base_resultado;
    Button button_calcular;
    TextView text_resultado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calculadora, container, false);
        setHasOptionsMenu(true);
        // Mapeamos los items
        text_num1 = (EditText)rootView.findViewById(R.id.textNumero1);
        spinner_base_num1 = (Spinner)rootView.findViewById(R.id.spinner_base_num1);
        spinner_operadores = (Spinner)rootView.findViewById(R.id.spinner_operacion);
        text_num2 = (EditText)rootView.findViewById(R.id.textNumero2);
        spinner_base_num2 = (Spinner)rootView.findViewById(R.id.spinner_base_num2);
        spinner_base_resultado = (Spinner)rootView.findViewById(R.id.spinner_base_resultado);
        button_calcular = (Button) rootView.findViewById(R.id.btnCalcular);
        text_resultado = (TextView)rootView.findViewById(R.id.text_resultado);

        // Evento del boton calcular
        button_calcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String numero1 = text_num1.getText().toString();
                String numero2 = text_num2.getText().toString();

                if(numero1.equals(""))
                {
                    Toast.makeText(getContext(),"Aún hay campos por llenar!!",Toast.LENGTH_LONG).show();
                    if (numero2.equals(""))
                    {
                        Toast.makeText(getContext(),"Aún hay campos por llenar!!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    if (numero2.equals(""))
                    {
                        Toast.makeText(getContext(),"Aún hay campos por llenar!!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //
                        String base_num1 = spinner_base_num1.getSelectedItem().toString();
                        String base_num2 = spinner_base_num2.getSelectedItem().toString();

                        String operador = spinner_operadores.getSelectedItem().toString();
                        String base_resultado = spinner_base_resultado.getSelectedItem().toString();
                        boolean bandera1 = false;
                        boolean bandera2 = false;
                        // Validamos que ambos números estén escritos en las bases indicadas
                        if (validar_cadena(numero1,Integer.parseInt(base_num1)))
                        {
                            bandera1 = true;
                        }
                        else
                        {
                            Toast.makeText(getContext(),"El número: "+numero1+" no está escrito en la base: "+base_num1,Toast.LENGTH_SHORT).show();
                        }

                        if (validar_cadena(numero2,Integer.parseInt(base_num2)))
                        {
                            bandera2 = true;
                        }
                        else
                        {
                            Toast.makeText(getContext(),"El número: "+numero2+" no está escrito en la base: "+base_num2,Toast.LENGTH_SHORT).show();
                        }

                        if (bandera1 && bandera2)
                        {
                             text_resultado.setText(division_sucesivas(operacion(base_10(numero1,base_num1),base_10(numero2,base_num2),operador),base_resultado));
                        }

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
                text_resultado.setText("");
                text_num1.setText("");
                text_num2.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // Metodo operación devuelve el resultado de num1 y num2
    public String operacion(String num1, String num2, String operador)
    {
        int num_1 = Integer.parseInt(num1);
        int num_2 = Integer.parseInt(num2);
        int resultado = 0;
        switch (operador)
        {
            case "+":
                Log.e("EZH",num1+" + "+num2);
                resultado = num_1 + num_2;
                break;
            case "-":
                Log.e("EZH",num1+" - "+num2);
                resultado = num_1 - num_2;
                break;
            case "*":
                Log.e("EZH",num1+" * "+num2);
                resultado = num_1 * num_2;
                break;
            case "/":
                Log.e("EZH",num1+" / "+num2);
                resultado = num_1 / num_2;
                break;
            default:
                resultado = 0;
                break;
        }
        Log.e("EZH","RESULTADO ----> "+resultado);
        return ""+resultado;

    }

    // devuelve el numero en base_final -- Metodo de divisiones sucesivas
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

    // Metodo que invierte los residuos
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

    // Devuelve en String el numero que pasa como parametro
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

    // Devuelve el numero equivalente a la letra
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

    // Valida el numero
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

}
