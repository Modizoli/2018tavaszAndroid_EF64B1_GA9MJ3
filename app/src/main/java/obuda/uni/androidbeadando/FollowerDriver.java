package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class FollowerDriver extends ModelBase implements IDriver {

    @Override
    public void drive( long elapsedMS, float velocityModifyer, ModelBase player ) {
        py += ( velocity * elapsedMS );
    }

    public FollowerDriver(){
        resourceName = "greencar";
    }
    public FollowerDriver(FollowerDriver fd){
        super(fd);
    }
}
