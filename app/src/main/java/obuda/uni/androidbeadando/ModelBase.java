package obuda.uni.androidbeadando;

/**
 * Created by Zoltán on 4/14/2018.
 */

public class ModelBase {
    public float px;
    public float py;
    public int width;
    public int height;
    public float velocity;
    public String resourceName;

    public ModelBase(){

    }

    public ModelBase(ModelBase mb){
        this.px = mb.px;
        this.py = mb.py;
        this.width = mb.width;
        this.height = mb.height;
        this.velocity = mb.velocity;
        this.resourceName = mb.resourceName;
    }
}
