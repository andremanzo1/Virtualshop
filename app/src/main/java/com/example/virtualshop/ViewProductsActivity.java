package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ViewProductsActivity extends AppCompatActivity implements ProductListAdapter.OnDeleteClickListener {
    private ProductViewModel productViewModel;
    private ProductListAdapter adapter;
    private boolean isAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        isAdmin = getIntent().getBooleanExtra("IS_ADMIN", false);

        RecyclerView recyclerView = findViewById(R.id.productsRecyclerView);
        adapter = new ProductListAdapter(this, this,isAdmin); // Pass the activity as the delete click listener
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Observe changes to the list of products
        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                // Update the UI with the new list of products
                adapter.setProducts(products);
            }
        });
        FloatingActionButton addProductButton = findViewById(R.id.addProductButton);
        if (isAdmin) {
            // Show admin-specific UI elements or perform admin-specific actions
            // For example, display the "Add Product" button
            addProductButton.setVisibility(View.VISIBLE);
        }else {
            // Hide the "Add Product" button for regular users
            addProductButton.setVisibility(View.GONE);
        }
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, e.g., open a new activity for adding products
                Intent addProductIntent = new Intent(ViewProductsActivity.this, AddProductActivity.class);
                startActivity(addProductIntent);
            }
        });
    }


    @Override
    public void onDeleteClick(Product product) {

        productViewModel.deleteProduct(product);
    }
}