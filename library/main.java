// ini adalah commit pertama saya di github

import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

  static Scanner scan = new Scanner(System.in);
  static Library library = new Library();

  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";

    while (isContinue.equals("y")) {
      showMenu();
      int selectedMenu = chooseMenu();

      if (selectedMenu == 0) {
        break;
      } else if (selectedMenu == 1) {
        showBooks();
      } else if (selectedMenu == 2) {
        addBook();
      } else if (selectedMenu == 3) {
        showMembers();
      } else if (selectedMenu == 4) {
        addMember();
      } else if (selectedMenu == 5) {
        borrowBook();
      } else if (selectedMenu == 6) {
        returnBook();
      } else {
        System.out.println("wrong input");
      }

      System.out.print("continue(y) ? ");
      isContinue = scan.next();
    }
  }

  public static void showMenu() {
    System.out.println("================================");
    System.out.println("1. show books list");
    System.out.println("2. add book");
    System.out.println("3. show members list");
    System.out.println("4. add member");
    System.out.println("5. borrow book");
    System.out.println("6. return book");
    System.out.println("0. Exit");
    System.out.println("================================");
  }

  public static void initLibraryData() {
    Book book1 = new Book();
    book1.Id("1");
    book1.Title("pemrograman java");

    Book book2 = new Book();
    book2.Id("2");
    book2.Title("pemrograman oop");

    Book book3 = new Book();
    book3.Id("3");
    book3.Title("pemrograman android");

    Member member1 = new Member();
    member1.setId("1");
    member1.setName("aka");

    Member member2 = new Member();
    member2.setId("2");
    member2.setName("budi");

    Member member3 = new Member();
    member3.setId("3");
    member3.setName("tono");

    library.books.add(book1);
    library.books.add(book2);
    library.books.add(book3);

    library.members.add(member1);
    library.members.add(member2);
    library.members.add(member3);
  }

  public static int chooseMenu() {
    int pilihan = 0;
    boolean validInput = false;
    
    while (!validInput) {
        try {
            System.out.print("choose menu : ");
            pilihan = scan.nextInt();
            validInput = true;
        } catch (InputMismatchException e) {
            System.out.println("Inputan salah, silahkan masukkan nomor yang ada pada menu.");
            scan.next();
        }
    }
    return pilihan;
}

  public static void showBooks() {
    for (Book book : library.books) {
      System.out.println(book.Id() + " " + book.Title());
    }
  }

  public static void addBook() {
    Book book = new Book();

    System.out.print("id : ");
    book.Id(scan.next());

    System.out.print("title : ");
    book.Title(scan.next());

    library.addBook(book);
  }

  public static void showMembers() {
    for (Member member : library.members) {
      System.out.println(member.getId() + " " + member.getName());
    }
  }

  public static void addMember() {
    Member member = new Member();

    System.out.print("id : ");
    member.setId(scan.next());

    System.out.print("name : ");
    member.setName(scan.next());

    library.addMember(member);
  }

  public static void borrowBook() {
    try {
      System.out.print("id member : ");
      String memberId = scan.next();
  
      System.out.print("id book : ");
      String bookId = scan.next();
  
      library.giveBook(bookId, memberId);
    } catch (Exception e) {
      System.out.println("Buku tidak ada pada library atau member tidak terdaftar");
    }
  }

  public static void returnBook() {
    try {
      System.out.print("id member : ");
      String memberId = scan.next();
  
      System.out.print("id book : ");
      String bookId = scan.next();
  
      library.receiveBook(memberId, bookId);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Terjadi error pada inputan");
    }
  }
}