package hidalgo.andres.roomtest.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Administrador on 21/01/2018.
 */

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ProductDao productDao();

    public static AppDatabase getAppDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "sample.db")
                    .addMigrations()
                    .build();
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'Fruit' ('id' INTEGER, 'name' TEXT, PRIMARY KEY('id'))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER");
        }
    };

    public void insertProduct(final ProductEntity product) {
        if (instance != null) {
            instance.runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            instance.productDao().insertProduct(product);
                        }
                    }
            );
        }
    }

    public void insertProducts(final List<ProductEntity> products) {
        if (instance != null) {
            instance.runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            instance.productDao().insertAll(products);
                        }
                    }
            );
        }
    }

    public ProductEntity getProduct(final int productId) {
        if (instance != null) {
            return instance.productDao().loadProduct(productId);
        }
        return null;
    }

    public List<ProductEntity> getAllProducts() {
        if (instance != null) {
            return instance.productDao().loadAllProducts();
        }
        return null;
    }

    public void updateProduct (final ProductEntity product) {
        if (instance != null) {
            instance.runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            instance.productDao().updateProduct(product);
                        }
                    }
            );
        }
    }

    public void deleteProduct (final ProductEntity product) {
        if (instance != null) {
            instance.runInTransaction(
                    new Runnable() {
                        @Override
                        public void run() {
                            instance.productDao().deleteProduct(product);
                        }
                    }
            );
        }
    }
}
