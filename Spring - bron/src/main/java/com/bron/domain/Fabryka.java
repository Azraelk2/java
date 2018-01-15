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
@NamedQueries({ @NamedQuery(name = "fabryka.selectAll", query = "SELECT f FROM Fabryka f"),
    @NamedQuery(name = "fabryka.selectByNazwa", query = "SELECT f FROM Fabryka f WHERE f.nazwa = :nazwa"),
    @NamedQuery(name = "fabryka.deleteAll", query = "DELETE FROM Fabryka") })
public class Fabryka {
  private long id;
  private String nazwa;
  private List<Bron> bron = new ArrayList<Bron>();
  

  public Fabryka() {
    super();
  }

  public Fabryka(String nazwa) {
    super();
    this.nazwa = nazwa;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Bron> getBron() {
    return bron;
  }

  public void setBron(List<Bron> bron) {
    this.bron = bron;
  }
}
