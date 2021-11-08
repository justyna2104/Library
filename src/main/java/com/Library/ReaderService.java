package com.Library;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ReaderService extends GenericDao<Reader> {

    public Boolean checkCheckOutAbility(Reader reader){
        Long amountOfBooks = (Long) entityManager.createQuery("select count(c) from CheckOut c where  c.reader.id = :readerId")
                .setParameter("readerId", reader.getId()).getSingleResult();
        System.out.println(amountOfBooks);
        if(amountOfBooks>4){
            return false;
        }else return true;
    }

    public void setUpLibraryCard() throws ParseException {
        Reader reader = new Reader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name.");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Enter your last name.");
        reader.setLastname(scanner.nextLine());
        System.out.println("Enter your address.");
        reader.setAddress(scanner.nextLine());
        System.out.println("Enter your phone number.");
        reader.setPhoneNumber(Integer.valueOf(scanner.nextLine()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Enter your date of birth.(YYYY/MM/DD)");
        String date = scanner.nextLine();
        reader.setBirthday(format.parse(date));
        addEntity(reader);
    }

    public List<Reader> findByName(String name){
        List<Reader> readers = entityManager.createQuery("select r from Reader r" +
                " where concat(r.firstname,' ', r.lastname) like :name").setParameter("name", "%"+name+"%").getResultList();
        return readers;
    }

    public void editLibraryCard(Reader reader) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        String choice = "0";
        while(choice != "6") {
            System.out.println("Press 1 if you wat to edit first name.");
            System.out.println("Press 2 if you wat to edit last name.");
            System.out.println("Press 3 if you wat to edit address.");
            System.out.println("Press 4 if you wat to edit phone number.");
            System.out.println("Press 5 if you want to edit date of birth");
            System.out.println("Press 6 if you're done editing.");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter your first name.");
                    String firstname= scanner.nextLine();
                    reader.setFirstname(firstname);
                    updateEntity(reader);
                    break;
                case "2":
                    System.out.println("Enter your last name.");
                    reader.setLastname(scanner.nextLine());
                    updateEntity(reader);
                    break;
                case "3":
                    System.out.println("Enter your address.");
                    reader.setAddress(scanner.nextLine());
                    updateEntity(reader);
                    break;
                case "4":
                    System.out.println("Enter your phone number.");
                    reader.setPhoneNumber(Integer.valueOf(scanner.nextLine()));
                    updateEntity(reader);
                    break;
                case "5":
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    System.out.println("Enter your date of birth.(YYYY/MM/DD)");
                    String date = scanner.nextLine();
                    reader.setBirthday(format.parse(date));
                    updateEntity(reader);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("There's no such a choice.");
                    break;
            }
        }
    }
}
