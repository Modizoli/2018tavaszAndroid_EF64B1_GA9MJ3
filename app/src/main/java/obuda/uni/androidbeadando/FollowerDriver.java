package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class FollowerDriver extends ModelBase implements ICollidable, IDriver {

    @Override
    public void drive( long elapsedMS ) {

    }

    @Override
    public void onCollision() {

    }

    public FollowerDriver(){}
    public FollowerDriver(FollowerDriver fd){
        super(fd);
    }
}
