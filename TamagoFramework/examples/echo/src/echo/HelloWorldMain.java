package echo;

import tamago.*;

public class HelloWorldMain {

    public static void main(String[] args) {
	ConsoleOut out = new ConsoleOut();
	EchoServer echo = new EchoServer();
	
	try {
	    echo.bind(out);
	} catch(ServiceBindException sbe) {
	    sbe.printStackTrace(System.err);
	}

	echo.echo("Hello");
	echo.echo("World");
    }
}

