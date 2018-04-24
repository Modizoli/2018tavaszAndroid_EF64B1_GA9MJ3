package obuda.uni.androidbeadando;

import android.util.SparseArray;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class ModelFactory {
    public static final int FUEL = 0;
    public static final int PEACEFUL_DRIVER = 1;
    public static final int RECKLESS_DRIVER = 2;
    public static final int FOLLOWER_DRIVER = 3;

    SparseArray<ModelBase> models;

    public ModelBase createModel( int type ) {
        return models.get(type);
    };

    public ModelFactory(){
        models = new SparseArray<ModelBase>();
        models.append( FUEL, new FuelModel() );
        models.append( PEACEFUL_DRIVER, new PeacefulDriver() );
        models.append( RECKLESS_DRIVER, new RecklessDriver() );
        models.append( FOLLOWER_DRIVER, new FollowerDriver() );
    }
}
