package hidalgo.andres.roomtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import hidalgo.andres.roomtest.db.AppDatabase;
import hidalgo.andres.roomtest.db.ProductEntity;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        new RoomTestTask().execute();
    }

    private class RoomTestTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... v) {
            Log.d("room", "doInBackground: ");
            db.insertProduct(new ProductEntity("name4", "description4", 4000));
            List<ProductEntity> products = db.getAllProducts();
            Log.d(TAG, "" + products.get(products.size() - 1).getId());
            Log.d(TAG, "" + products.size());
            return 0;
        }

        @Override
        protected void onPostExecute(Integer i) {
            Log.d("room", "onPostExecute: ");
        }
    }
}
