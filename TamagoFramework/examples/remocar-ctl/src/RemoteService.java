import tamago.*;

public interface RemoteService extends Service {
    public void on();
    public void off();
    public void forward();
    public void backward();
    public void left();
    public void right();
}
