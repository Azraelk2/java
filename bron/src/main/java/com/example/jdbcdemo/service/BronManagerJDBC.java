package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.example.jdbcdemo.domain.Bron;

public class BronManagerJDBC implements BronManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableBron = "CREATE TABLE Bron(id bigint GENERATED BY DEFAULT AS IDENTITY, nazwa varchar(20) UNIQUE, kaliber integer, cena double, typ varchar(20))";

	private PreparedStatement addBronStmt;
	private PreparedStatement deleteAllBronieStmt;
	private PreparedStatement deleteBronStmt;
	private PreparedStatement updateBronStmt;
	private PreparedStatement searchBronStmt;
	private PreparedStatement getAllBronieStmt;
	
    private static Statement stmt;

	private Statement statement;

	public BronManagerJDBC() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Bron".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableBron);

			addBronStmt = connection.prepareStatement("INSERT INTO Bron (nazwa, kaliber, cena, typ) VALUES (?, ?, ?, ?)");
			deleteAllBronieStmt = connection.prepareStatement("DELETE FROM Bron");
			deleteBronStmt = connection.prepareStatement("DELETE FROM Bron WHERE nazwa=?");//tu byla nazwa
			updateBronStmt = connection.prepareStatement("UPDATE Bron SET kaliber=?, cena=?, typ=? WHERE nazwa=?");
			searchBronStmt = connection.prepareStatement("SELECT * FROM Bron WHERE nazwa=?");
			getAllBronieStmt = connection.prepareStatement("SELECT id, nazwa, kaliber, cena, typ FROM Bron");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	////////////
	public void read(DefaultTableModel model){
		
		   try {
			   connection = DriverManager.getConnection(url);
	            stmt = connection.createStatement();
	         	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	            
			try {
		
			ResultSet result = stmt.executeQuery("SELECT id, nazwa, kaliber, cena, typ FROM Bron");
     
 	Bron FULL =new Bron();
      while(result.next()) {
        FULL.setId(result.getInt("id"));
        FULL.setNazwa(result.getString("nazwa"));
        FULL.setKaliber(result.getInt("kaliber"));
        FULL.setCena(result.getDouble("cena"));
        FULL.setTyp(result.getString("typ"));
      
        Object[] full = {FULL.getId(),FULL.getNazwa(),FULL.getKaliber(),FULL.getCena(),FULL.getTyp()};
        model.addRow(full); 
      }
      } catch (SQLException e) {
          System.err.println("Blad przy wykonywaniu SELECT");
          e.printStackTrace();
      }       
        try {   
        	stmt.close();
  
        } catch (SQLException e) {
            System.err.println("SQLException");
            e.printStackTrace();
        }

//read KONIEC	
}
	////////////
	public void clearBronie() {
		try {
			deleteAllBronieStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addBron(Bron bron) {
		int count = 0;
		try {
			addBronStmt.setString(1, bron.getNazwa());
			addBronStmt.setInt(2, bron.getKaliber());
			addBronStmt.setDouble(3, bron.getCena());
			addBronStmt.setString(4, bron.getTyp());

			count = addBronStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateBron(Bron bron) {
		int count = 0;
		try {
			updateBronStmt.setInt(1, bron.getKaliber());
			updateBronStmt.setDouble(2, bron.getCena());
			updateBronStmt.setString(3, bron.getTyp());
			updateBronStmt.setString(4, bron.getNazwa());	
			
			count = updateBronStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Bron searchBron(String nazwa) {

		Bron f = new Bron();
		try {
			searchBronStmt.setString(1,nazwa);
			ResultSet rs = searchBronStmt.executeQuery();

			while (rs.next()) {
				f.setId(rs.getInt("id"));
				f.setNazwa(rs.getString("nazwa"));
				f.setKaliber(rs.getInt("kaliber"));
				f.setCena(rs.getDouble("cena"));
				f.setTyp(rs.getString("typ"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}
	@Override
	public List<Bron> getAllBronie() {
		List<Bron> bronie = new ArrayList<Bron>();

		try {
			ResultSet rs = getAllBronieStmt.executeQuery();

			while (rs.next()) {
				Bron b = new Bron();
				b.setId(rs.getInt("id"));
				b.setNazwa(rs.getString("nazwa"));
				b.setKaliber(rs.getInt("kaliber"));
				b.setCena(rs.getDouble("cena"));
				b.setTyp(rs.getString("typ"));
				bronie.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bronie;
	}

	public int deleteBron(String nazwa) {
		int count = 0;
		try {
			deleteBronStmt.setString(1, nazwa);

			count = deleteBronStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}

