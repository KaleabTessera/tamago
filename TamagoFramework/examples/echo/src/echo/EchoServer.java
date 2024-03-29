package echo;

import tamago.*;

public class EchoServer extends BasicComponent
    implements EchoService,
	       RequireConsoleService {

    /* attributes */

    // private data

    // properties

    // bindings
    ConsoleService console;


    /* construction */

    public EchoServer() {
	console = null;
    }

    /* require methods */

    public void bind(ConsoleService console) throws ServiceBindException {
	this.console = console;
    }


    /* provide methods */

    public void echo(String message) {
	if(console!=null) {
	    console.println(message);
	} // TODO : throw an exception
    }

}
