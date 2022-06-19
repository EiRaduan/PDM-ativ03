package com.example.appsupermecado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


import android.os.Bundle;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Produto> listaproduto;

    public Adapter(List<Produto> listaproduto){
        this.listaproduto = listaproduto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = listaproduto.get(position);
        holder.nome.setText(produto.getNome());
        //CONVERTER DOUBLE PARA STRING
        holder.preco.setText(Double.toString(produto.getPreco()));
        //CONVERTER INT PARA STRING
        holder.quantidade.setText(Integer.toString(produto.getQuantidade()));

    }

    @Override
    public int getItemCount() {
        return listaproduto.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox nome;
        EditText preco, quantidade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.ck_nome);
            preco = itemView.findViewById(R.id.edt_preco);
            quantidade = itemView.findViewById(R.id.edt_quantidade);
        }
    }
}