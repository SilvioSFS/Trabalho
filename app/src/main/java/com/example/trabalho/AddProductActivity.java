package com.example.trabalho;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private Button buttonSaveProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextProductName = findViewById(R.id.edittext_product_name);
        editTextProductPrice = findViewById(R.id.edittext_product_price);
        buttonSaveProduct = findViewById(R.id.button_save_product);

        buttonSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString();
                String productPriceString = editTextProductPrice.getText().toString();

                if (!productName.isEmpty() && !productPriceString.isEmpty()) {
                    double productPrice = Double.parseDouble(productPriceString);
                    Product newProduct = new Product(productName, productPrice);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newProduct", newProduct);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}