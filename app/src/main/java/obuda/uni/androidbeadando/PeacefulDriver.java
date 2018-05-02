package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class PeacefulDriver extends ModelBase implements ICollidable, IDriver {
    @Override
    public void drive( long elapsedMS ) {
        py += ( velocity * elapsedMS ) / 1000;
    }

    @Override
    public void onCollision() {

    }

    public PeacefulDriver(){
        resourceName = "greencar";
    }
    public PeacefulDriver(PeacefulDriver pd){
        super(pd);
        resourceName = "greencar";
    }
}
