package com.example.appsupermecado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Produto> produtos = new ArrayList<Produto>();
    Adapter adapter = new Adapter(produtos);

    private EditText edt_orcamento, edt_nome, edt_valor;
    private Button btn_inserir, btn_salvar, btn_apagar;

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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

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