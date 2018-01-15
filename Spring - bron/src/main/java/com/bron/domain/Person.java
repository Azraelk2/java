package com.bron.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "person.selectAll", query = "SELECT p FROM Person p"),
	@NamedQuery(name = "person.selectByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
	@NamedQuery(name = "person.deleteAll", query = "DELETE FROM Person") })
public class Person {
  private long id;
  private String imie, nazwisko, email;
  private List<Bron> bron = new ArrayList<Bron>();

  public Person() {
    super();
  }

  public Person(String imie, String nazwisko, String email) {
    super();
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.email = email;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(unique = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getImie() {
    return imie;
  }

  public void setImie(String imie) {
    this.imie = imie;
  }

  public String getNazwisko() {
    return nazwisko;
  }

  public void setNazwisko(String nazwisko) {
    this.nazwisko = nazwisko;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Bron> getBron() {
    return bron;
  }

  public void setBron(List<Bron> bron) {
    this.bron = bron;
  }
}
