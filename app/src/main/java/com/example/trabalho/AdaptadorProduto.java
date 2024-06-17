package com.example.trabalho;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AdaptadorProduto extends ArrayAdapter<Produto> {

    public AdaptadorProduto(Context context, ArrayList<Produto> produtos) {
        super(context, 0, produtos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_produto, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textview_product_name);
        TextView textViewPrice = convertView.findViewById(R.id.textview_product_price);

        textViewName.setText(product.getName());
        textViewPrice.setText(String.valueOf(product.getPrice()));

        return convertView;
    }
}
