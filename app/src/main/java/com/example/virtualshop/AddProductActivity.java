package com.example.virtualshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;  // Import ViewModelProvider
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private Button addProductButton;

    private ProductViewModel productViewModel;  // Declare ProductViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Initialize UI elements
        productNameEditText = findViewById(R.id.productNameEditText);
        productPriceEditText = findViewById(R.id.productPriceEditText);
        addProductButton = findViewById(R.id.addProductButton);

        // Initialize ProductViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Set up click listener for the add product button
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve product details from EditText fields
                String productName = productNameEditText.getText().toString();
                double productPrice = Double.parseDouble(productPriceEditText.getText().toString());

                // Create a new Product object
                Product newProduct = new Product(productName, productPrice);

                // Save the new product to the database using ProductViewModel
                productViewModel.insertProduct(newProduct);

                // Finish the activity (you can also navigate back to the previous screen)
                finish();
            }
        });
    }
}