package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

import static android.R.attr.name;
import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class app1 extends AppCompatActivity {

    int quantity = 0;
    private EditText customer;
    CheckBox whippedCream, chocolate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app1);
        whippedCream = (CheckBox) findViewById(R.id.whipped_check_box);
        chocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        customer = (EditText) findViewById(R.id.cust_name_edit);
    }


    public void submitOrder(View view) {


        boolean whipped1 = whippedCream.isChecked();
        boolean chocolate1 = chocolate.isChecked();
        String customer1 = customer.getText().toString();
        int price = calculatePrice(whipped1, chocolate1);
        String priceMessage = createOrderSummary(customer1, price, whipped1, chocolate1);


        displayMessage(priceMessage);
    }
        public void emailOrder(View View)  {
            boolean whipped1 = whippedCream.isChecked();
            boolean chocolate1 = chocolate.isChecked();
            String customer1 = customer.getText().toString();
            int price = calculatePrice(whipped1, chocolate1);
            String priceMessage = createOrderSummary(customer1, price, whipped1, chocolate1);


            //String priceMessage1 = priceMessage;
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "Iceberg Icecream order for " + customer1 );
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);


            }return;
        }


    /**
     * Calculates the price of the order.
     * @param whipped1
     * @param chocolate1
     */


    private int calculatePrice(boolean whipped1,boolean chocolate1) {
        int price = 10;
        if (whipped1) {
            price = price + 3;
        }
        if (chocolate1) {
            price = price + 5;
        } else {//nothing to do
            }
            return quantity * price;
        }

    private String createOrderSummary(String customer1, int price, boolean whipped1, boolean chocolate1){
    String priceMessage = "Name:" + customer1;
        priceMessage  += "\n Add Whipped cream ? \n";
        priceMessage+= whipped1 + "\n Add Chocolate ? \n" + chocolate1;
        priceMessage+= "\n quantity:" + quantity;
        priceMessage+= "\n Total Rs: " + price;
        priceMessage += "\n Thank You!";
        return priceMessage;
}


    /**
     * This method is called when the plus button is clicked.
     */

    public void increment(View view) {
    if (quantity == 100){

        Toast.makeText(this, "You cannot have more than 100 Cups of Coffee",Toast.LENGTH_SHORT).show();
        return;
    }
        quantity = quantity + 1;
        display(quantity);

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0){

            Toast.makeText(this,"You cannot have less than 1 Cup of Coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);

    }









    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.QUANTITY_text_view);
        quantityTextView.setText("" + number);
    }

   /* private void displayMessage1(String i, int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.QUANTITY_text_view);
        quantityTextView.setText("" + i);
    }*/

    /**
     * This method displays the given price on the screen.
     */


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}
