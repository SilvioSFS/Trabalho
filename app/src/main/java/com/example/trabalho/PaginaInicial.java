package com.example.trabalho;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PaginaInicial extends AppCompatActivity {

    private Button buttonAddProduct;
    private ListView listViewProducts;
    private AdaptadorProduto adaptadorProduto;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        buttonAddProduct = findViewById(R.id.button_Adicionar);
        listViewProducts = findViewById(R.id.listview_products);

        productList = new ArrayList<>();
        adaptadorProduto = new AdaptadorProduto(this, productList);
        listViewProducts.setAdapter(adaptadorProduto);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaInicial.this, AddProductActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Product newProduct = (Product) data.getSerializableExtra("newProduct");
            productList.add(newProduct);
            adaptadorProduto.notifyDataSetChanged();
        }
    }
}