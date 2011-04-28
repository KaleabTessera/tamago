package echo;

import tamago.*;

public class ConsoleOut extends BasicComponent
    implements ConsoleService {

    /* attributes */

    // private data

    // properties

    // bindings

    /* construction */

    public ConsoleOut() {
    }

    /* require methods */

    /* provide methods */

    public void print(String s) {
	System.out.print(s);
    }

    public void println(String s) {
	System.out.println(s);
    }

}
