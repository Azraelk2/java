package com.bron.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bron.domain.Bron;
import com.bron.domain.Fabryka;
import com.bron.domain.Person;

@Component
@Transactional
public class BronManagerClass implements BronManager {

  @Autowired
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addBron(Bron bron) {
    sessionFactory.getCurrentSession().persist(bron);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Bron> getAllBronie() {
    return sessionFactory.getCurrentSession().getNamedQuery("bron.selectAll").list();
  }
  
  @Override
  public void removeAllBronie() {
	sessionFactory.getCurrentSession().getNamedQuery("bron.deleteAll").executeUpdate();
  }

  @Override
  public void updateBron(Bron bron) {
    sessionFactory.getCurrentSession().saveOrUpdate(bron);
  }

  @Override
  public void removeBron(Bron bron) {
    sessionFactory.getCurrentSession().delete(bron);
  }

  @Override
  public Bron findBronByNazwa(String nazwa) {
    return (Bron) sessionFactory.getCurrentSession().getNamedQuery("bron.selectByNazwa").setString("nazwa", nazwa).uniqueResult();
  }

  @Override
  public void addFabryka(Fabryka fabryka) {
    sessionFactory.getCurrentSession().persist(fabryka);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Fabryka> getAllFabryki() {
    return sessionFactory.getCurrentSession().getNamedQuery("fabryka.selectAll").list();
  }
  
  @Override
  public void removeAllFabryki() {
	sessionFactory.getCurrentSession().getNamedQuery("fabryka.deleteAll").executeUpdate();
  }

  @Override
  public void updateFabryka(Fabryka fabryka) {
    sessionFactory.getCurrentSession().saveOrUpdate(fabryka);
  }

  @Override
  public void removeFabryka(Fabryka fabryka) {
    sessionFactory.getCurrentSession().delete(fabryka);
  }

  @Override
  public Fabryka findFabrykaByNazwa(String nazwa) {
    return (Fabryka) sessionFactory.getCurrentSession().getNamedQuery("fabryka.selectByNazwa").setString("nazwa", nazwa).uniqueResult();
  }

  @Override
  public void addPerson(Person person) {
    sessionFactory.getCurrentSession().persist(person);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Person> getAllPersons() {
    return sessionFactory.getCurrentSession().getNamedQuery("person.SelectAll").list();
  }

  @Override
  public void removeAllPersons() {
	sessionFactory.getCurrentSession().getNamedQuery("person.deleteAll").executeUpdate();
  }
  
  @Override
  public void updatePerson(Person person) {
    sessionFactory.getCurrentSession().saveOrUpdate(person);
  }

  @Override
  public void removePerson(Person person) {
    sessionFactory.getCurrentSession().delete(person);
  }

  @Override
  public Person findPersonByEmail(String email) {
    return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.selectByEmail").setString("email", email).uniqueResult();
  }
}
