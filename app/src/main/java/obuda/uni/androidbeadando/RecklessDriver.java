package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class RecklessDriver extends ModelBase implements IDriver {
    @Override
    public void drive( long elapsedMS, float velocityModifyer, ModelBase player ) {
        py += ( velocity * elapsedMS );
    }

    public RecklessDriver(){
        resourceName = "yellowcar";
    }
    public RecklessDriver(RecklessDriver rd){
        super(rd);
    }
}
