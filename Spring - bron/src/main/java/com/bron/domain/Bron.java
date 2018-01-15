package com.bron.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.bron.domain.Person;
import com.bron.domain.Fabryka;

@Entity
@NamedQueries({ @NamedQuery(name = "bron.selectAll", query = "SELECT b FROM Bron b"),
    @NamedQuery(name = "bron.selectByNazwa", query = "SELECT b FROM Bron b WHERE b.nazwa = :nazwa"),
    @NamedQuery(name = "bron.deleteAll", query = "DELETE FROM Bron") })
public class Bron {
	private long id;	
	private String nazwa;
	private int kaliber;
	private double cena;
	private String typ;
	private Person person;
	private Fabryka fabryka;
  
  public Bron() {
    super();
  }

  public Bron(String nazwa, int kaliber, double cena, String typ, Person person, Fabryka fabryka) {
		super();
		this.nazwa = nazwa;
		this.kaliber = kaliber;
		this.cena = cena;
		this.typ = typ;
		this.person = person;
		this.fabryka = fabryka;
	}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(unique = true)
  public String getNazwa() {
    return nazwa;
  }

  public void setNazwa(String nazwa) {
    this.nazwa = nazwa;
  }

  public int getKaliber() {
	  return kaliber;
  }

  public void setKaliber(int kaliber) {
	  this.kaliber = kaliber;
  }

  public double getCena() {
	  return cena;
  }

  public void setCena(double cena) {
	  this.cena = cena;
  }
  
  public String getTyp() {
	  return typ;
  }

  public void setTyp(String typ) {
	  this.typ = typ;
  }

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public Person getPerson() {
     return person;
   }

   public void setPerson(Person person) {
     this.person = person;
  }

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public Fabryka getFabryka() {
     return fabryka;
   }

   public void setFabryka(Fabryka fabryka) {
     this.fabryka = fabryka;
  }
}
