package march10october.com.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import corp.cencosud.dscl_omnichannel_int2336.onlinestockoutputmessage.Stock_online_resp;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getName();
    Stock_online_resp stockDisplayInfo = new Stock_online_resp();
    ArrayList<String> stockListDisplayStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView skuDesc = (TextView) findViewById(R.id.id_sku_desc);
        TextView skuEAN = (TextView) findViewById(R.id.id_sku_label);
        TextView normalPrice = (TextView) findViewById(R.id.id_normal_price);
        TextView discountedPrice= (TextView) findViewById(R.id.id_discounted_price);

        skuDesc.setText( skuDesc.getText() + "skuDescription");
        skuEAN.setText(skuEAN.getText() + "SKY EAN");
            Integer formatted = Integer.parseInt("111");
            normalPrice.setText(normalPrice.getText() + formatted.toString());
            Integer formatted1 = Integer.parseInt("222");
            discountedPrice.setText(discountedPrice.getText() + formatted1.toString());

        skuDesc.setText("SKUi description ");
        stockDisplayInfo =getDisplayObject();
        onPostExecute(" ");

        ListView stockList = (ListView) findViewById(R.id.listView);


        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, stockListDisplayStrings);
        // Set The Adapter
        stockList.setAdapter(arrayAdapter);



    }

    void getStocks(Stock_online_resp stockDisplayInfo )
    {
        for ( Stock_online_resp.Stock stock: stockDisplayInfo.getStock() )
        {
            stockListDisplayStrings.add("Stock Actual: "+ stock.getStock_act()
                +"\r\n" + "Color: " + stock.getColor()
                +"\r\n"+ "Talla: "+ stock.getTalla());
        }

    }

    protected void onPostExecute(String result) {

        stockListDisplayStrings = new ArrayList<>();

        try
        {
            TableLayout table = (TableLayout) findViewById(R.id.id_display_stock_table);
            LayoutInflater inflater = getLayoutInflater();

            for (Stock_online_resp.Stock stock : stockDisplayInfo.getStock()) {
                LinearLayout row = (LinearLayout) inflater.inflate(R.layout.stock_details_row, table, false);

                TextView t1 = (TextView) row.findViewById(R.id.id_stock_details_sku);
                t1.setText("" + stock.getSku());
                TextView t2 = (TextView) row.findViewById(R.id.id_stock_details_stock_act);
                t2.setText("" + stock.getStock_act());
                TextView t3 = (TextView) row.findViewById(R.id.id_stock_details_size);
                t3.setText("" + stock.getTalla());
                TextView t4 = (TextView) row.findViewById(R.id.id_stock_details_color);
                t4.setText("" + stock.getColor());


                table.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            }
        }
        catch (Exception q)
        {
            q.printStackTrace();
            Log.i(TAG, " server error.");
        }


    }













//    void getAnimalNames() {
//        animalsNameList.add("DOG \r\nDOG \r\nAnother puppy");
//        animalsNameList.add("CAT");
//        animalsNameList.add("HORSE");
//        animalsNameList.add("ELEPHANT");
//        animalsNameList.add("LION");
//        animalsNameList.add("COW");
//        animalsNameList.add("MONKEY");
//        animalsNameList.add("DEER");
//        animalsNameList.add("RABBIT");
//        animalsNameList.add("BEER");
//        animalsNameList.add("DONKEY");
//        animalsNameList.add("LAMB");
//        animalsNameList.add("GOAT");
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Stock_online_resp getDisplayObject()
    {
        Stock_online_resp stock_online_resp = new Stock_online_resp();

        try
        {
            stock_online_resp.setSku(BigInteger.valueOf(Integer.parseInt(("1111").toString())));
            stock_online_resp.setCod_resp(BigInteger.valueOf(Integer.parseInt(("222").toString())));
            stock_online_resp.setEan(BigInteger.valueOf(Integer.parseInt(("3333").toString())));
            stock_online_resp.setMsg_resp(("444"));
            stock_online_resp.setDesc_art(("5555").toString());
            stock_online_resp.setDesc_local(("6666").toString());

            List<Stock_online_resp.Stock> stList = new ArrayList<Stock_online_resp.Stock>();
            for (int i = 0; i < 2; i++)
            {
                    Stock_online_resp.Stock st = new Stock_online_resp.Stock();
                    st.setColor(("color" + i));
                    st.setTalla(("talla" + i));
                    st.setStock_ant(BigInteger.valueOf(Integer.parseInt(("11").toString())));
                    st.setVentas(BigInteger.valueOf(Integer.parseInt(("22").toString())));
                    st.setNota_credito(BigInteger.valueOf(Integer.parseInt(("33").toString())));
                    st.setStock_act(BigInteger.valueOf(Integer.parseInt(("44").toString())));
                    st.setMes_antiguedad(BigInteger.valueOf(Integer.parseInt(("44").toString())));
                    st.setSku(BigInteger.valueOf(Integer.parseInt(("55").toString())));
                    stList.add(st);
            }
            stock_online_resp.setStock(stList);
        }
        catch(Exception ex)
        {
            Log.i(TAG, "Not able to parse response properly");
        }
        return stock_online_resp;
    }
}
