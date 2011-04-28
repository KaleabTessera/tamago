import tamago.*;

public interface ReceiverService extends Service {
    void receive(Command command);
}

