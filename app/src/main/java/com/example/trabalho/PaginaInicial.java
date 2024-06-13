package com.example.trabalho;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

private Button buttonAddProduct;
private ListView listViewProducts;
private ProductAdapter productAdapter;
private ArrayList<Product> productList;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    buttonAddProduct = findViewById(R.id.button_add_product);
    listViewProducts = findViewById(R.id.listview_products);

    productList = new ArrayList<>();
    productAdapter = new ProductAdapter(this, productList);
    listViewProducts.setAdapter(productAdapter);

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
        productAdapter.notifyDataSetChanged();
    }
}
}