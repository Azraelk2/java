package com.example.jdbcdemo.service;

import java.awt.BorderLayout;

//import java.util.ArrayList;

//import java.awt.*;
import java.awt.event.*; 
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
//import java.io.*;

import com.example.jdbcdemo.domain.Bron;

class Widok extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	BronManagerJDBC a = new BronManagerJDBC();
		
	private DefaultTableModel model;
	private  JTable table;
	
	private JTextField  nazwa;
	private JTextField  kaliber;
	private JTextField  cena;
	private JTextField  typ;
	private JLabel nazwaL ;
	private JLabel kaliberL;
	private JLabel cenaL ;
	private JLabel typL ;
	private JTextField  searchh;
	private JLabel szukajj ;
	
	public Widok() {
		
		  super();
		
		  JPanel inputPanel = new JPanel();
		  
		  this.model = new DefaultTableModel(){
			
				private static final long serialVersionUID = 1L;

			
				@Override
		        public boolean isCellEditable(int row, int column) {
		           
		           return false;
		        }
		    
		   };
		  
		   model.addColumn("Id ");
	       model.addColumn("Nazwa ");
	       model.addColumn("Kaliber  ");
	       model.addColumn("Cena ");
	       model.addColumn("Typ ");

	    a.read(model);
	    
	    table = new JTable(model);
	    table.removeColumn(table.getColumnModel().getColumn(0));
	    
	    nazwa = new JTextField(15);
	    kaliber = new JTextField(5);
	    cena = new JTextField(15);
	    typ = new JTextField(10);
	    searchh= new JTextField(10);
	    
	    JButton addButton = new JButton("Dodaj");
	    JButton removeButton = new JButton("Usuń");
	    JButton updateButton = new JButton("Zmień");
//	    JButton searchButton = new JButton("Szukaj");
//	    JButton refreshButton = new JButton("Odśwież");
	  //JLabels
	    nazwaL = new JLabel("Nazwa");
	    kaliberL = new JLabel("Kaliber");
	    cenaL= new JLabel("Cena");
	    typL=new  JLabel("Typ");
	    szukajj = new JLabel("Szukaj:");
	    
	    table.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e) {
	    	    if (e.getClickCount() == 2) {
	    	    
	    	    
	    	     nazwa.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),1)));
	    	     kaliber.setText(Integer.toString((Integer) (table.getModel().getValueAt(table.getSelectedRow(),2))));
	    	     cena.setText(Double.toString((Double) (table.getModel().getValueAt(table.getSelectedRow(),3))));
	    	     typ.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),4)));
	    	     addButton.setEnabled(false);
	    	     nazwa.setEnabled(false);
	    	    }
	    	  }
	    	});
	    
	    searchh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            	searchhKeyReleased(evt);
            }
        });
	    
//	    JButton addButton = new JButton("Dodaj");
	    addButton.addActionListener(new ActionListener() {

	      public void actionPerformed(ActionEvent event) {
	    	  Bron cc = new Bron();
	    	  cc.setNazwa(nazwa.getText());
	    	  cc.setKaliber(Integer.parseInt(kaliber.getText()));
	    	  cc.setCena(Double.parseDouble(cena.getText()));
	    	  cc.setTyp(typ.getText()); 	  
	    	  a.addBron(cc);
	    	  setmodel();   
	      }
	    }); 
	    
//	    JButton removeButton = new JButton("Usuń");

	    removeButton.addActionListener(new ActionListener() {

	      public void actionPerformed(ActionEvent event) {	    	 
	    	  int row = table.getSelectedRow();
	    	  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Wyberz rekord by go usunac");}else{
	    	  String  nazwa1;
	    	  nazwa1 = nazwa.getText();
	    	  int answer = JOptionPane.showConfirmDialog(
	    			  inputPanel,
	    			    "Jestes pewien ze chcesz usunac ten rekord",
	    			    "WARNING",
	    			    JOptionPane.YES_NO_OPTION);  
	    	  if(answer == JOptionPane.YES_OPTION){ 
	    	  a.deleteBron(nazwa1);
	    	  model.removeRow(table.getSelectedRow());
  			  addButton.setEnabled(true);
  			  nazwa.setEnabled(true);
	    	  }
      
	    	  }
	      }
	    });
	    
//	    JButton updateButton = new JButton("Zmień");
	    updateButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent event) {
	    		Bron bron = new Bron();
	    		int row = table.getSelectedRow();
	    		if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na rekord ktory chcesz zaktualizowac");}else{
//	    			bron.setId((int) (table.getModel().getValueAt(table.getSelectedRow(),0))); 
	    			bron.setNazwa(nazwa.getText());
	    			bron.setKaliber(Integer.parseInt(kaliber.getText()));
	    			bron.setCena(Double.parseDouble(cena.getText()));
	    			bron.setTyp(typ.getText());
	    			
	    			a.updateBron(bron);
	    			setmodel();
	    			addButton.setEnabled(true);
	    			nazwa.setEnabled(true);
	    		}
	    	}       });
	    
	    inputPanel.setLayout(new BorderLayout());
	    JPanel subPanel = new JPanel(); 
	    JScrollPane tableContainer = new JScrollPane(table);
	    inputPanel.add(tableContainer, BorderLayout.NORTH);
	    subPanel.add(addButton);
	    subPanel.add(removeButton);	 
	    subPanel.add(updateButton);	  
	    subPanel.add(szukajj);	
	    subPanel.add(searchh);
	    subPanel.add(nazwaL);
	    subPanel.add(nazwa);
	    subPanel.add(kaliberL);
	    subPanel.add(kaliber);
	    subPanel.add(cenaL);
	    subPanel.add(cena);
	    subPanel.add(typL);
	    subPanel.add(typ);

	    inputPanel.add(subPanel, BorderLayout.SOUTH);
	    add(inputPanel);
	    
	}
	
	private void searchhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased

        DefaultTableModel table1 = (DefaultTableModel)table.getModel();
        String search = searchh.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table1);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
	
	void setmodel(){
		
		  this.model = new DefaultTableModel(){
				
				private static final long serialVersionUID = 1L;
		
				@Override
		        public boolean isCellEditable(int row, int column) {
		           
		           return false;
		        }
		    
		    };
		    model.addColumn("Id");
		    model.addColumn("Nazwa");
		    model.addColumn("Kaliber");
		    model.addColumn("Cena");
		    model.addColumn("Typ");
		    a.read(model);
		    this.table.setModel(model);
		    table.removeColumn(table.getColumnModel().getColumn(0));

	}

	
	
	public static void main(String[] args) {
		JFrame f = new Widok();
	    f.setSize(1460,640);
//	    f.setLocation(100,100);
	    f.setVisible(true);
		
	}
}
