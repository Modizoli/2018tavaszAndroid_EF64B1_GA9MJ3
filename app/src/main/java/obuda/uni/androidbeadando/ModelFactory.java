package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class ModelFactory {
    public enum ThingsEnum{
        fuel,
        peacefulDriver,
        recklessDriver,
        followerDriver
    }

    public ModelBase createModel( ThingsEnum type ) {
        ModelBase res = null;

        switch( type ) {
            case fuel:
                FuelModel fm = new FuelModel();

                res = fm;
                break;
            case peacefulDriver:
                PeacefulDriver pd = new PeacefulDriver();

                res = pd;
            case recklessDriver:
                RecklessDriver rd = new RecklessDriver();

                res = rd;
            case followerDriver:
                FollowerDriver fd = new FollowerDriver();

                res = fd;
                break;
        };

        return res;
    };
}
