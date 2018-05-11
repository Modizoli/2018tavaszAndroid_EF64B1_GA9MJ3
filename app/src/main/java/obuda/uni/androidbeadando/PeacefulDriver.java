package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class PeacefulDriver extends ModelBase implements IDriver {
    @Override
    public void drive( long elapsedMS, float velocityModifyer, ModelBase player ) {
        py += ( velocity * elapsedMS ) / 1000;
    }

    public PeacefulDriver(){
        resourceName = "greencar";
    }
    public PeacefulDriver(PeacefulDriver pd){
        super(pd);
        resourceName = "greencar";
    }
}
