package hidalgo.andres.roomtest.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Administrador on 21/01/2018.
 */

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    List<ProductEntity> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> products);

    @Query("select * from products where id = :productId")
    ProductEntity loadProduct(int productId);
}
