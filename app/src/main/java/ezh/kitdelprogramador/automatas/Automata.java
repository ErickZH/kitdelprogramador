package ezh.kitdelprogramador.automatas;

/**
 * Clase Automata
 */
public class Automata
{
    protected int estado;
    public Automata(int nuevo_estado)
    {
        estado = nuevo_estado;
    }
    public Automata(){}

    public int get_estado()
    {
        return estado;
    }

    public void set_estado(int nuevo_estado)
    {
        estado = nuevo_estado;
    }
}
