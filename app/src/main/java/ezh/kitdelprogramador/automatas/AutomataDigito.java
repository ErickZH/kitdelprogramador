package ezh.kitdelprogramador.automatas;

import android.util.Log;

/**
 * Clase AutomataDigito que herada de Automata
 */
public class AutomataDigito extends Automata
{
    private int base_origen;
    public AutomataDigito(int new_estado,int nueva_base)
    {
        estado = new_estado;
        base_origen = nueva_base;
    }

    public int transicion(char simbolo)
    {
        Log.e("EZH","Nuevo estado -> "+estado);
        Log.e("EZH","Base origen -> "+base_origen);
        int nuevo_estado = 0;
        if (base_origen <= 10)// Automata digito
        {
            switch (estado) {
                case 1:
                    if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
                case 2:
                    if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
                case 3:
                    if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
            }

        }
        else // Automata digito con letras
        {
            switch (estado)
            {
                case 1:
                    if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else if (new ManejoCadena(base_origen).isLetter(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es letra");
                        nuevo_estado = 3;
                    }

                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
                case 2:
                    if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else if (new ManejoCadena(base_origen).isLetter(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es letra");
                        nuevo_estado = 3;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
                case 3:
                    if (new ManejoCadena(base_origen).isLetter(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es letra");
                        nuevo_estado = 3;
                    }
                    else if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
                case 4:
                    if (new ManejoCadena(base_origen).isLetter(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es letra");
                        nuevo_estado = 3;
                    }
                    else if (new ManejoCadena(base_origen).isDigito(simbolo))
                    {
                        Log.e("EZH","El simbolo -> "+simbolo+" es digito");
                        nuevo_estado = 2;
                    }
                    else
                    {
                        nuevo_estado = -1;
                    }
                    break;
            }
        }
        return nuevo_estado;
    }


}
