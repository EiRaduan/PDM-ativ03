package com.example.appsupermecado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import android.content.SharedPreferences;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private List<Produto> produtos = new ArrayList<Produto>();
    private final static String ARQUIVO_PREFERENCIA ="ArquivoPreferencia";
    Adapter adapter = new Adapter(produtos);

    private EditText edt_orcamento, edt_nome, edt_valor;
    private Button btn_inserir, btn_salvar, btn_apagar;
    private CheckBox ck_nome;
    private EditText edt_preco;
    private EditText edt_quantidade;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LINKAGEM
        recyclerView = findViewById(R.id.recyclerView);
        edt_orcamento = findViewById(R.id.edt_orcamento);
        edt_nome = findViewById(R.id.edt_produto);
        edt_valor = findViewById(R.id.edt_valor);
        btn_inserir = findViewById(R.id.btn_inserir);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_apagar = findViewById(R.id.btn_apagar);
        ck_nome = findViewById(R.id.ck_nome);

        edt_preco = findViewById(R.id.edt_preco);
        edt_quantidade = findViewById(R.id.edt_quantidade);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);


        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "CLICK LONGO NO INTEM PARA REMOVER", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getApplicationContext(), produtos.get(position).getNome(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(getApplicationContext(), "Clique longo!", Toast.LENGTH_LONG).show();
                                produtos.remove(position);
                                adapter.notifyItemRemoved(position);
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }));

        btn_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_nome.getText().toString().isEmpty() || edt_valor.getText().toString().isEmpty() || edt_orcamento.getText().toString().isEmpty()) {
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle(R.string.app_name);
                    janela.setMessage("Todos os campos devem ser preenchidos!!!");
                    janela.setPositiveButton("OK", null);
                    janela.show();
                } else {
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle(R.string.app_name);
                    janela.setMessage("Todos os dados estão corretos?");
                    janela.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            inserirItem();

                            /*
                            *
                            *
                            * */
                        }
                    });

                    janela.setNegativeButton("não", null);
                    janela.show();
                }
            }
        });
    }

        public void inserirItem(){
            String nome = edt_nome.getText().toString();
            Double valor = Double.parseDouble(edt_valor.getText().toString());
            Integer quantidade = 0;

            produtos.add(new Produto(nome, valor, quantidade));
            adapter.notifyItemInserted(produtos.size());
        }


}