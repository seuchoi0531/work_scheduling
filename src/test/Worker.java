package test;

import java.util.Vector;
import javax.swing.DefaultListModel;

public class Worker {
    private DefaultListModel<String> nameListModel;
    private DefaultListModel<String> jobListModel;
    private DefaultListModel<String> timeListModel;
    private Vector<Worker> workervector;
    String name;
	String job;
	String time;
    Worker(String name, String job, String time){
    	this.name = name;
    	this.job = job;
    	this.time = time;
	}
}
