package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class FollowerDriver extends ModelBase implements IDriver {

    @Override
    public void drive( long elapsedMS, float velocityModifyer, ModelBase player ) {
        float delta_x = px - player.px;
        px += delta_x * velocity * 0.1;

        py += (
                velocity * elapsedMS
        );
    }

    public FollowerDriver(){
        resourceName = "purplecar";
    }
    public FollowerDriver(FollowerDriver fd){
        super(fd);
    }
}
