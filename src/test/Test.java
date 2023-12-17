package test;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;
import java.util.Vector;
import java.text.AttributedString;

public class Test extends JFrame {
    private static DefaultListModel<String> nameListModel;
    private static DefaultListModel<String> jobListModel;
    private static DefaultListModel<String> timeListModel;
    private static JList<String> namelist;
    private static JList<String> joblist;
    private static JList<String> timelist;
    private static JPanel deletePnl;
    private static Vector<String> timevector = new Vector<String>();
    private static Vector<JTextField> timetextfieldvector = new Vector<JTextField>();
    private static Vector<JCheckBox> timecheckvector = new Vector<JCheckBox>(); // D L E d l e
    private static String infoString = "";
    private static JPanel pnl1;
    private static Vector<Schedule> schedule;

    public Test() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 700);

        Container cp = this.getContentPane();
        GridBagLayout gBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        cp.setLayout(gBag);
        gbc.fill = GridBagConstraints.BOTH;
        
        pnl1 = new JPanel();
        pnl1.setPreferredSize(new Dimension(700, 700));
        pnl1.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "근무표"));
        pnl1.setLayout(new BorderLayout());
        

        JPanel pnl2 = new JPanel(); // 조건란 패널
        pnl2.setPreferredSize(new Dimension(350, 700)); // 조건란 사이즈
        BoxLayout conditions = new BoxLayout(pnl2, BoxLayout.Y_AXIS);
        pnl2.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "조건"));
        pnl2.setLayout(conditions);

        // 조건란에 추가할 것들
        JPanel p0 = createTimeCondition();
        p0.setPreferredSize(new Dimension(350, 200));
        p0.setBorder(new LineBorder(Color.black, 3));
        JPanel p1 = createWorkerCondition();
        p1.setPreferredSize(new Dimension(350, 100));
        p1.setBorder(new LineBorder(Color.black, 3));
        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.setPreferredSize(new Dimension(350, 400));
        p2.setBorder(new LineBorder(Color.black, 3));

        nameListModel = new DefaultListModel<>();
        jobListModel = new DefaultListModel<>();
        timeListModel = new DefaultListModel<>();

        namelist = new JList<>(nameListModel);
        joblist = new JList<>(jobListModel);
        timelist = new JList<>(timeListModel);

        addListSelectionListener(namelist, joblist, timelist);
        addListSelectionListener(joblist, namelist, timelist);
        addListSelectionListener(timelist, namelist, joblist);

        JTextArea ta = new JTextArea();
        deletePnl = new JPanel();
        //BoxLayout deleteBox = new BoxLayout(deletePnl, BoxLayout.Y_AXIS);
        p2.add(namelist);
        p2.add(joblist);
        p2.add(timelist);
        p2.add(deletePnl);

        pnl2.add(p0);
        pnl2.add(p1);
        pnl2.add(p2);


        // 근무표에 추가할 것들
        JPanel p3 = createGenerateButton();
        pnl1.add(p3, BorderLayout.NORTH);
        
        JPanel p4 = createTables();
        //p4.setPreferredSize(new Dimension(700, 650));
        p4.setBorder(new LineBorder(Color.black, 3));

        JScrollPane scrollPane = new JScrollPane(p4);
        pnl1.add(scrollPane, BorderLayout.CENTER);
        
        
        
        
        cp.add(pnl1, gbc);
        cp.add(pnl2, gbc);

        this.pack();
    }

    private static JPanel createTimeCondition() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        addLabel(panel, gbc, "근무파트", 0, 0, 3, 1, true);
        addLabel(panel, gbc, "시작시간", 3, 0, 3, 1, true);
        addLabel(panel, gbc, "근무시간", 6, 0, 3, 1, true);
        addLabel(panel, gbc, "Day", 0, 1, 3, 1, false);
        addLabel(panel, gbc, "Lunch", 0, 2, 3, 1, false);
        addLabel(panel, gbc, "Evening", 0, 3, 3, 1, false);
        addLabel(panel, gbc, "day Like", 0, 4, 3, 1, false);
        addLabel(panel, gbc, "lunch Like", 0, 5, 3, 1, false);
        addLabel(panel, gbc, "evening Like", 0, 6, 3, 1, false);
        
        JButton setTime = new JButton("set");
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        panel.add(setTime, gbc);

        for(int i = 1; i < 7; i++) {
        	for(int j = 3; j < 7; j += 3)
        		addTextField(panel, gbc, j, i, 3, 1);
        }
        setTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(timevector.isEmpty()) {
            		for(int i = 0;i < timetextfieldvector.size();i++)
            			timevector.add(timetextfieldvector.get(i).getText());
            	}
            	else {
            		for(int i = 0;i < timetextfieldvector.size();i++)
            			timevector.set(i, timetextfieldvector.get(i).getText());
            	}
            }
        });
    	return panel;
    }
    private static JPanel createWorkerCondition() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        addLabel(panel, gbc, "이름", 0, 0, 3, 1, true);
        addLabel(panel, gbc, "직급", 3, 0, 3, 1, true);
        addLabel(panel, gbc, "D", 6, 0, 1, 1, true);
        addLabel(panel, gbc, "L", 7, 0, 1, 1, true);
        addLabel(panel, gbc, "E", 8, 0, 1, 1, true);
        addLabel(panel, gbc, "d", 9, 0, 1, 1, true);
        addLabel(panel, gbc, "l", 10, 0, 1, 1, true);
        addLabel(panel, gbc, "e", 11, 0, 1, 1, true);

        JTextField nameTf = new JTextField(7);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        panel.add(nameTf, gbc);
        
        String[] items = {"전문가", "숙련자", "초보자"};
        JComboBox cb = new JComboBox(items);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        panel.add(cb, gbc);
        
        for(int i = 6;i < 12;i++)
        	addCheckBox(panel, gbc, i, 1, 1, 1);
        
        JButton addWorker = new JButton("add");
        gbc.gridx = 12;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        panel.add(addWorker, gbc);
        
        addWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTf.getText();
                String jobLevel = (String) cb.getSelectedItem();
                String time = "";
                if (timecheckvector.get(0).isSelected())
                    time += "D";
                if (timecheckvector.get(1).isSelected())
                    time += "L";
                if (timecheckvector.get(2).isSelected())
                    time += "E";
                if (timecheckvector.get(3).isSelected())
                    time += "d";
                if (timecheckvector.get(4).isSelected())
                    time += "l";
                if (timecheckvector.get(5).isSelected())
                    time += "e";

                nameListModel.addElement(name);
                jobListModel.addElement(jobLevel);
                timeListModel.addElement(time);

                JButton deleteBt = new JButton("삭제");
                deleteBt.setPreferredSize(new Dimension(80, 15));
                deletePnl.add(deleteBt);

                deleteBt.setActionCommand(name + "_" + jobLevel + "_" + time);

                deleteBt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String actionCommand = ((JButton) e.getSource()).getActionCommand();
                        
                        String[] parts = actionCommand.split("_");
                        String name = parts[0];
                        String jobLevel = parts[1];
                        String time = parts[2];

                        int index = findIndex(name, jobLevel, time);

                        if (index != -1) {
                            nameListModel.remove(index);
                            jobListModel.remove(index);
                            timeListModel.remove(index);
                            deletePnl.remove(deleteBt);
                            deletePnl.revalidate();
                            deletePnl.repaint();
                        }
                    }
                });

                nameTf.setText("");
                cb.setSelectedIndex(0);
                for(JCheckBox checkbox : timecheckvector)
                	checkbox.setSelected(false);
                
            }
        });
        return panel;
    }

    private static void addLabel(JPanel panel, GridBagConstraints gbc, String name, int x, int y, int width, int height, boolean center) {
    	JLabel label = new JLabel(name);
    	label.setBorder(new LineBorder(Color.BLACK));
    	if(name.charAt(0) =='D' || name.charAt(0) =='L' || name.charAt(0) =='E')
    		underlineFirstLetter(label);
    	if(name.charAt(0) =='d' || name.charAt(0) =='l' || name.charAt(0) =='e')
    		underlineFirstLetter(label);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        if (center)
        	label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, gbc);
    }
    private static void addTextField(JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
    	JTextField textfield = new JTextField(7);
    	textfield.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        panel.add(textfield, gbc);
    	timetextfieldvector.add(textfield);
    }
    private static void addCheckBox(JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
    	JCheckBox checkbox = new JCheckBox();
    	timecheckvector.add(checkbox);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        panel.add(checkbox, gbc);
    }
    private static void underlineFirstLetter(JLabel label) {
        String text = label.getText();

        if (text != null && !text.isEmpty()) {
            String htmlText = "<html><u>" + text.charAt(0) + "</u>" + text.substring(1) + "</html>";
            label.setText(htmlText);
        }
    }
    private static int findIndex(String name, String jobLevel, String time) {
        for (int i = 0; i < nameListModel.size(); i++) {
            if (nameListModel.getElementAt(i).equals(name) &&
                jobListModel.getElementAt(i).equals(jobLevel) &&
                timeListModel.getElementAt(i).equals(time)) {
                return i;
            }
        }
        return -1;
    }
    private static void addListSelectionListener(JList<String> sourceList, JList<String> list1, JList<String> list2) {
        sourceList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = sourceList.getSelectedIndex();
                    list1.setSelectedIndex(selectedIndex);
                    list2.setSelectedIndex(selectedIndex);
                }
            }
        });
    }
    
    private static JPanel createGenerateButton() {
    	JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 50));
        panel.setBorder(new LineBorder(Color.black, 3));
    	panel.setLayout(new BorderLayout());
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(100, 50));

        panel.add(generateButton, BorderLayout.EAST);
        generateButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		infoString = "";
        		for(int i = 0;i < timevector.size();i++)
        			infoString += timevector.elementAt(i) + "<br>";
        		infoString += "<br>";
        		for(int i = 0;i < nameListModel.size(); i++) {
        			infoString += nameListModel.getElementAt(i) + " ";
        			infoString += jobListModel.getElementAt(i) + " ";
        			infoString += timeListModel.getElementAt(i) + "<br>";
        		}
        		System.out.println(infoString);
        		JPanel tablesPanel = createTables();
                
                updateTablesPanel(tablesPanel);

                pnl1.remove(1);
                pnl1.add(tablesPanel, BorderLayout.CENTER);

                pnl1.revalidate();
                pnl1.repaint();
                
        		//To Do
                schedule = makeSchedule();
            }
        });
    	return panel;
    }
    private static JPanel createTables() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < 40; i++) {
            //JPanel innerPanel = generateTable(schedule.elementAt(i));
        	JPanel innerPanel = new JPanel();
            innerPanel.setBorder(new LineBorder(Color.black, 3));

            JLabel label = new JLabel("Table " + (i + 1));
            innerPanel.add(label);

            panel.add(innerPanel);
            
        }
    	return panel;
    }
    
    private static Vector<Schedule> makeSchedule(){
    	Vector<Schedule> schedules = new Vector<Schedule>();
    	
    	return schedules;
    }
    private static void updateTablesPanel(JPanel tablesPanel) {
        JLabel label = (JLabel) tablesPanel.getComponent(0);
        label.setText("<html>"+infoString+"</html>");
    }
    
    private static JPanel generateTable(Schedule schedule) {
    	JPanel panel = new JPanel();
    	
    	return panel;
    }
    
    public static void main(String[] args) {
         Test t = new Test();
    }
}