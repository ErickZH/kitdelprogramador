/**
 * Clase ManejoCadena que basicamente sirve para validar la cadena
 */
package ezh.kitdelprogramador.automatas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejoCadena
{
    private int base_origen;
    public ManejoCadena(int base)
    {
        base_origen = base;
    }
    //Definimos la expresiones regulares para cada metodo

    public boolean isDigito(char digito)
    {
        String str = String.valueOf(digito);
        return validarCadena(str, get_Patron()); //return false;
    }

    public boolean isLetter(char digito)
    {
        String str = String.valueOf(digito);
        return validarCadena(str, get_Patron());
    }
    private boolean validarCadena(String cadena, String patron)
    {

        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(cadena);
        return m.matches();
    }

    private String get_Patron()
    {
        String patron = "";
        switch (base_origen)
        {
            case 2:
                patron = "[0-1]";
                break;
            case 3:
                patron = "[0-2]";
                break;
            case 4:
                patron = "[0-3]";
                break;
            case 5:
                patron = "[0-4]";
                break;
            case 6:
                patron = "[0-5]";
                break;
            case 7:
                patron = "[0-6]";
                break;
            case 8:
                patron = "[0-7]";
                break;
            case 9:
                patron = "[0-8]";
                break;
            case 10:
                patron = "[0-9]";
                break;
            case 11:
                patron = "[A-A0-9]";
                break;
            case 12:
                patron = "[A-B0-9]";
                break;
            case 13:
                patron = "[A-C0-9]";
                break;
            case 14:
                patron = "[A-D0-9]";
                break;
            case 15:
                patron = "[A-E0-9]";
                break;
            case 16:
                patron = "[A-F0-9]";
                break;
        }
        return patron;
    }
}
