package ezh.kitdelprogramador;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ezh.kitdelprogramador.fragmentos.FragmentCalculadora;
import ezh.kitdelprogramador.fragmentos.FragmentConvertidor;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener
{
    // Declaración de variables globales
    private Fragment fragments[] = new Fragment[]{new FragmentCalculadora(),new FragmentConvertidor()};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Llamada al metodo setTabs
        setTabs();
        // Manejador de fragmentos
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for (Fragment fragment : fragments)
        {
            transaction.add(R.id.main_calculadora,fragment).hide(fragment);
        }
        //Mostramos el fragment 0
        transaction.show(fragments[0]).commit();
    }
    // Metodo setTabs que asigna los Tabs al ActionBar
    private void setTabs()
    {
        // Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Calculadora").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Convertidor").setTabListener(this));
    }
    // Implementacion de los metodos de TabListener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
    {
        for (Fragment fragment : fragments)
        {
            ft.hide(fragment);
        }
        ft.show(fragments[tab.getPosition()]);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }
}
