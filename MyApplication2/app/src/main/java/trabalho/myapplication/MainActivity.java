package trabalho.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import trabalho.myapplication.broadcast.ConnectivityReceiver;
import trabalho.myapplication.broadcast.MyApplication;
import trabalho.myapplication.list.Produto;
import trabalho.myapplication.list.ProdutoAdapter;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private List<Produto> produtos = new ArrayList<>();
    private ListView lstView;

    public CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProdutosFake();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);


        checkConnectivity();


        lstView = (ListView) findViewById(R.id.listview);
        ProdutoAdapter adp
                = new ProdutoAdapter(getApplicationContext(), produtos);
        lstView.setAdapter(adp);

    }

    private void loadProdutosFake() {
        produtos.add(new Produto(1, "Foto 3x4", "R$ 10,00", "http://www.colorkit.com.br/image/cache/data/fotoprodutos/CARTEIRINHAS-500x400.jpg"));
        produtos.add(new Produto(2, "Caneca Personalizada", "R$ 25,00", "https://static.expanssiva.com.br/uploads/imagens/produtos/lindas_canecas_de_dia_dos_pais.jpg"));
        produtos.add(new Produto(3, "Camisa Branca Personalizada", "R$ 20,00", "https://img.elo7.com.br/product/original/D5BE70/camiseta-personalizada-dia-dos-pais-personalizada.jpg"));

    }

    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityRecceiver(this);
    }

    public void checkConnectivity() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    public void showSnack(boolean isConnected) {
        if (isConnected) {
            Snackbar.make(coordinatorLayout, getString(R.string.internet_connected), Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(coordinatorLayout, getString(R.string.no_internet_connected), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.settings), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }).setActionTextColor(Color.RED)
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onNetworkChange(boolean inConnected) {
        showSnack(inConnected);
    }
}
