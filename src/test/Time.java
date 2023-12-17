package test;

import java.util.Vector;

public class Time {
	String Dstart;
	String Lstart;
	String Estart;
	String dstart;
	String lstart;
	String estart;
	String Dworking;
	String Lworking;
	String Eworking;
	String dworking;
	String lworking;
	String eworking;
	Time(Vector<String> timevector){
		this.Dstart = timevector.elementAt(0);
		this.Dworking = timevector.elementAt(1);
		this.Lstart = timevector.elementAt(2);
		this.Lworking = timevector.elementAt(3);
		this.Estart = timevector.elementAt(4);
		this.Eworking = timevector.elementAt(5);
		this.dstart = timevector.elementAt(6);
		this.dworking = timevector.elementAt(7);
		this.lstart = timevector.elementAt(8);
		this.lworking = timevector.elementAt(9);
		this.estart = timevector.elementAt(10);
		this.eworking = timevector.elementAt(11);
	}
}
