package no.hvl.dat250.jpa.tutorial.creditcards.driver;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    Address address = new Address();
    address.setNumber(28);
    address.setStreet("Inndalsveien");

    Bank bank = new Bank();
    bank.setName("Pengebank");

    Pincode code = new Pincode();
    code.setCode("123");
    code.setCount(1);

    CreditCard card1 = new CreditCard();
    card1.setNumber(12345);
    card1.setBalance(-5000);
    card1.setCreditLimit(-10000);
    card1.setPincode(code);
    card1.setOwningBank(bank);

    CreditCard card2 = new CreditCard();
    card2.setNumber(123);
    card2.setBalance(1);
    card2.setCreditLimit(2000);
    card2.setPincode(code);
    card2.setOwningBank(bank);

    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    customer.getAddresses().add(address);
    customer.getCreditCards().add(card1);
    customer.getCreditCards().add(card2);

    em.persist(address);
    em.persist(bank);
    em.persist(code);
    em.persist(card1);
    em.persist(card2);
    em.persist(customer);
  }
}
