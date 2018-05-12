package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class RecklessDriver extends ModelBase implements IDriver {
    float angle = 0;

    @Override
    public void drive( long elapsedMS, float velocityModifyer, ModelBase player ) {
        px += Math.sin(Math.toRadians( angle += 20 ));

        if(angle < 360)
            angle = 0;

        py += ( velocity * elapsedMS );
    }

    public RecklessDriver(){
        resourceName = "yellowcar";
    }
    public RecklessDriver(RecklessDriver rd){
        super(rd);
    }
}
