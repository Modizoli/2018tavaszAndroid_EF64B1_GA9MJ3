package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class PeacefulDriver extends ModelBase implements ICollidable, IDriver {
    @Override
    public void drive() {

    }

    @Override
    public void onCollision() {

    }

    public PeacefulDriver(){
        resourceName = "greencar";
    }
}
