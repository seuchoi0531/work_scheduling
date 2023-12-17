package test;

import java.util.Vector;
import javax.swing.DefaultListModel;

public class Schedule {
    private Time time;
    private Vector<Worker> workervector = new Vector<Worker>();
    private Vector<Vector<String>> result = new Vector<Vector<String>>();
	Schedule(Vector<String> timevector, 
			DefaultListModel<String> nameListModel, 
			DefaultListModel<String> jobListModel, 
			DefaultListModel<String> timeListModel){
		this.time = new Time(timevector);
		for(int i = 0;i<nameListModel.size();i++)
			this.workervector.add(new Worker(nameListModel.elementAt(i), 
					jobListModel.elementAt(i), 
					timeListModel.elementAt(i)));
		this.workervector = workerVectorSort(this.workervector);
	}
	private Vector<Worker> workerVectorSort(Vector<Worker> workervector) {
		Vector<Worker> result = workervector;
		for(int i = 0;i < result.size() - 1;i++) {
			for(int j = i + 1;j < result.size();j++) {
				Worker w1 = result.get(i);
				Worker w2 = result.get(j);
				String s1 = w1.job;
				String s2 = w2.job;
				if (s1.equals("숙련자")){
					if(s2.equals("전문가")) {
						result.set(i,  w2);
						result.set(j,  w1);
					}
				}else if (s1.equals("초보자")){
					if(s2.equals("전문가") || s2.equals("숙련자")) {
						result.set(i,  w2);
						result.set(j,  w1);
					}
				}
			}
		}
		
		return result;
	}

}
