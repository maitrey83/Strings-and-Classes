/*
 * File: CountingIsFun.java
 * ================================================================
 * A program that shows off how we can use counters.
 */
import acm.program.*;

public class CountingIsFun extends ConsoleProgram {
	public void run() {
		/* How much fun?  Size-32 bold font fun! */
		setFont("DejaVuSerif-BOLD-32");
		
		Counter c1 = new Counter(0);
		Counter c2 = new Counter(137);
		println(c1);
		println(c2);
		
		c1.increment();
		c1.increment();
		c1.increment();
		c2.increment();
		
		println(c1);
		println(c2);
	}
}
