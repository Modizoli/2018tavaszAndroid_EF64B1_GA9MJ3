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

    public ModelBase createModel( int type, int px ) {
        ModelBase model = null;

        // Copy ctor downcast; crashes if using base copy ctor.
        switch(type){
            case PEACEFUL_DRIVER:
                model = new PeacefulDriver( (PeacefulDriver ) models.get( PEACEFUL_DRIVER ) );
                break;
            case RECKLESS_DRIVER:
                model = new RecklessDriver( (RecklessDriver ) models.get( RECKLESS_DRIVER ) );
                break;
            case FOLLOWER_DRIVER:
                model = new FollowerDriver( (FollowerDriver ) models.get( FOLLOWER_DRIVER ) );
                break;
            default:
                break;
        }


        model.px = px;
        model.velocity = 000.1f;

        return model;
    };

    public ModelFactory(){
        models = new SparseArray<ModelBase>();
        models.append( FUEL, new FuelModel() );
        models.append( PEACEFUL_DRIVER, new PeacefulDriver() );
        models.append( RECKLESS_DRIVER, new RecklessDriver() );
        models.append( FOLLOWER_DRIVER, new FollowerDriver() );
    }
}
