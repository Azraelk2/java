package com.bron.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bron.domain.Bron;
import com.bron.domain.Person;
import com.bron.domain.Fabryka;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class BronManagerTest {

	@Autowired
	 BronManagerClass bronManager;
	
	private static final double _PRECISION = 0.01;
  	private final static String NAZWA_1 = "Ak-47";
	private final static int KALIBER_1 = 7;
	private final static double CENA_1 = 1999.99;
	private final static String TYP_1 = "Palna";
	private final static String NAZWA_4 = "M4A4";
	
	private final static String NAZWA_2 = "Glock";
	private final static int KALIBER_2 = 5;
	private final static double CENA_2 = 2999.99;
	private final static String TYP_2 = "Palna";
	
	private final static String NAZWA_3 = "Desert Eagle";
	private final static int KALIBER_3 = 5;
	private final static double CENA_3 = 2999.99;
	private final static String TYP_3 = "Palna";

	private static final String NAZWAF_1 = "Trump Inc.";
	private static final String NAZWAF_2 = "Miscrosoft";
	private static final String NAZWAF_3 = "Orlen";
	private static final String NAZWAF_4 = "Wodnik";

	private static final String IMIE_1 = "Tadeusz";
	private static final String NAZWISKO_1 = "Kowalski";
	private static final String EMAIL_1 = "ttt.kowalski@gmail.com";

	private static final String IMIE_2 = "Waldemar";
	private static final String NAZWISKO_2 = "Knutt";
	private static final String EMAIL_2 = "www.knutt@gmail.com";

	private static final String IMIE_3 = "Bartosz";
	private static final String IMIE_4 = "Kamil";
	private static final String NAZWISKO_3 = "Kiepski";
	private static final String EMAIL_3 = "bbb.kiepski@gmail.com";
	
	Fabryka fabryka1 = new Fabryka(NAZWAF_1);
	Fabryka fabryka2 = new Fabryka(NAZWAF_2);
	Fabryka fabryka3 = new Fabryka(NAZWAF_3);
	
	Person person1 = new Person(IMIE_1, NAZWISKO_1, EMAIL_1);
	Person person2 = new Person(IMIE_2, NAZWISKO_2, EMAIL_2);
	Person person3 = new Person(IMIE_3, NAZWISKO_3, EMAIL_3);
	
	Bron bron1 = new Bron(NAZWA_1, KALIBER_1, CENA_1, TYP_1, new Person(), new Fabryka());
	Bron bron2 = new Bron(NAZWA_2, KALIBER_2, CENA_2, TYP_2, person2, fabryka2);
	Bron bron3 = new Bron(NAZWA_3, KALIBER_3, CENA_3, TYP_3, person3, fabryka3);
	
  @Test
  public void checkAddBron() {
	  	bronManager.removeAllBronie();
		bronManager.addBron(bron1);
		
		List<Bron> bronie = bronManager.getAllBronie();
		Bron bronRetrieved = bronie.get(0);
		
		assertEquals(NAZWA_1, bronRetrieved.getNazwa());
		assertEquals(KALIBER_1, bronRetrieved.getKaliber());
		assertEquals(CENA_1, bronRetrieved.getCena(), 5);
		assertEquals(TYP_1, bronRetrieved.getTyp());
	    bronManager.removeBron(bronRetrieved);
  }

  @Test
  public void checkGetAllBronie() {
    int bronieCounter = bronManager.getAllBronie().size();
    assertThat(bronieCounter, either(is(0)).or(is(1)));
  }
  
  @Test
  public void checkUpdateBron() {
	  	bronManager.removeAllBronie();
		Bron brontt = new Bron(NAZWA_1, KALIBER_2, CENA_2, TYP_2, person1, fabryka1);
		bronManager.addBron(bron1);
		
		bronManager.updateBron(brontt);
		
		List<Bron> bronie = bronManager.getAllBronie();
		Bron bronRetrieved = bronie.get(0);
		
		assertEquals(NAZWA_1, bronRetrieved.getNazwa());
		assertEquals(KALIBER_2, bronRetrieved.getKaliber());
		assertEquals(CENA_2, bronRetrieved.getCena(), 5);
		assertEquals(TYP_2, bronRetrieved.getTyp());
	    bronManager.removeBron(bronRetrieved);
  }

  @Test
  public void checkRemoveBron() {
    int Broncounter = bronManager.getAllBronie().size();
    Bron bronRetrieved = bronManager.getAllBronie().get(Broncounter - 1);
    bronManager.removeBron(bronRetrieved);

    int BronCounter2 = bronManager.getAllBronie().size();
    assertEquals(Broncounter - 1, BronCounter2);
  }

  @Test
  public void checkSearchBronByName() {
	Bron brontt = new Bron(NAZWA_1, KALIBER_2, CENA_2, TYP_2, person1, fabryka1);
    bronManager.addBron(brontt);

    String nazwa = brontt.getNazwa();
    Bron bronRetrieved = bronManager.findBronByNazwa(nazwa);
    assertEquals(bronRetrieved, brontt);

    bronManager.removeBron(bronRetrieved);
  }

  //fabryki
  @Test
  public void checkAddFabryka() {
	  	bronManager.removeAllFabryki();
		bronManager.addFabryka(fabryka1);
		
		List<Fabryka> fabryki = bronManager.getAllFabryki();
		Fabryka fabrykaRetrieved = fabryki.get(0);
		
		assertEquals(NAZWAF_1, fabrykaRetrieved.getNazwa());
	    bronManager.removeFabryka(fabrykaRetrieved);
  }

  @Test
  public void checkGetAllFabryki() {
    int fabrykiCounter = bronManager.getAllFabryki().size();
    assertThat(fabrykiCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void checkUpdateFabryka() {
	  	bronManager.removeAllFabryki();
	  	Fabryka fabrykatt = new Fabryka(NAZWAF_4);
		bronManager.addFabryka(fabryka1);

		bronManager.updateFabryka(fabrykatt);
		
		List<Fabryka> fabryki = bronManager.getAllFabryki();
		Fabryka fabrykaRetrieved = fabryki.get(0);
		
		assertEquals(NAZWAF_4, fabrykaRetrieved.getNazwa());
	    bronManager.removeFabryka(fabrykaRetrieved);
  }

  @Test
  public void checkRemoveFabryka() {
	    int Fabrykacounter = bronManager.getAllFabryki().size();
	    Fabryka fabrykaRetrieved = bronManager.getAllFabryki().get(Fabrykacounter - 1);
	    bronManager.removeFabryka(fabrykaRetrieved);

	    int Fabrykacounter2 = bronManager.getAllFabryki().size();
	    assertEquals(Fabrykacounter - 1, Fabrykacounter2);
  }

  @Test
  public void checkSearchFabryka() {
		Fabryka fabrykatt = new Fabryka(NAZWAF_1);
	    bronManager.addFabryka(fabrykatt);

	    String nazwa = fabrykatt.getNazwa();
	    Fabryka fabrykaRetrieved = bronManager.findFabrykaByNazwa(nazwa);
	    assertEquals(fabrykaRetrieved, fabrykatt);

	    bronManager.removeFabryka(fabrykaRetrieved);
  }

  //persons
  @Test
  public void checkAddPerson() {
	  	bronManager.removeAllPersons();
		bronManager.addPerson(person1);
		
		List<Person> persons = bronManager.getAllPersons();
		Person personRetrieved = persons.get(0);
		
		assertEquals(IMIE_1, personRetrieved.getImie());
		assertEquals(NAZWISKO_1, personRetrieved.getNazwisko());
		assertEquals(EMAIL_1, personRetrieved.getEmail());
	    bronManager.removePerson(personRetrieved);
  }

  @Test
  public void checkGetAllPersons() {
    int personsCounter = bronManager.getAllPersons().size();
    assertThat(personsCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void checkUpdatePerson() {
	  	bronManager.removeAllPersons();
	  	Person persontt = new Person(IMIE_4, NAZWISKO_1, EMAIL_1);
		bronManager.addFabryka(fabryka1);

		bronManager.updatePerson(persontt);
		
		List<Person> persons = bronManager.getAllPersons();
		Person personRetrieved = persons.get(0);
		
		assertEquals(IMIE_1, personRetrieved.getImie());
		assertEquals(NAZWISKO_1, personRetrieved.getNazwisko());
		assertEquals(EMAIL_1, personRetrieved.getEmail());
	    bronManager.removePerson(personRetrieved);
  }
  @Test
  public void checkRemovePerson() {
	    int Personcounter = bronManager.getAllPersons().size();
	    Person personRetrieved = bronManager.getAllPersons().get(Personcounter - 1);
	    bronManager.removePerson(personRetrieved);

	    int Personcounter2 = bronManager.getAllPersons().size();
	    assertEquals(Personcounter - 1, Personcounter2);
  }

  @Test
  public void checkSearchPerson() {
	  	bronManager.removeAllPersons();
	  	Person persontt = new Person(IMIE_4, NAZWISKO_1, EMAIL_1);
		bronManager.addPerson(person1);

	    String email = persontt.getEmail();
	    Person personRetrieved = bronManager.findPersonByEmail(email);
	    assertEquals(personRetrieved, persontt);

	    bronManager.removePerson(personRetrieved);
  }

}
