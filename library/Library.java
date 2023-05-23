// abstaction
// override polymorphism

import java.util.ArrayList;

abstract class List {
  public abstract void addBook(Book book); 
  public abstract void addMember(Member member);
  public abstract Boolean isBookIdExist(String id);
  public abstract Boolean isMemberIdExist(String id);
  public abstract void giveBook(String bookId, String memberId);
  public abstract void receiveBook(String bookId, String memberId);
  public abstract Book getBookById(String id);
}

class Library extends List{
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Book> jurnal = new ArrayList<Book>();
  public ArrayList<Member> members = new ArrayList<Member>();

  @Override
  public void addBook(Book book) {
    if (!isBookIdExist(book.Id())) {
      this.books.add(book);
    }
  }

  @Override
  public void addMember(Member member) {
    if (!isMemberIdExist(member.getId())) {
      this.members.add(member);
    }
  }

  @Override
  public Boolean isBookIdExist(String id) {
    Boolean isExist = false;
    try {
      for (Book book : this.books) {
        if (book.Id().equals(id)) {
          System.out.println("Id book sudah digunakan");
          isExist = true;
        }
      }
      for (Book book : this.jurnal) {
        if (book.Id().equals(id)) {
          System.out.println("Id book sudah digunakan");
          isExist = true;
        }
      }
      return isExist;
    } catch (NullPointerException e) {
      System.out.println("Terjadi kesalahan input pada id book");
      return isExist;
    }
  }

  @Override
  public Boolean isMemberIdExist(String id) {
    Boolean isExist = false;
    for (Member member : this.members) {
      if (member.getId().equals(id)) {
        System.out.println("Id member sudah digunakan");
        isExist = true;
      }
    }
    return isExist;
  }

  @Override
  public void giveBook(String bookId, String memberId) {
    Book book = this.getBookById(bookId);
    
    Member member = this.getMemberById(memberId);
    if (member != null) {
      this.jurnal.add(book);
      this.books.remove(book);

      int memberIndex = this.getMemberIndex(member);
    
      this.members.get(memberIndex).borrowedBooks.add(book);
    }
  }

  @Override
  public void receiveBook(String memberId, String bookId) {
    try {
      Member member = this.getMemberById(memberId);
    int memberIndex = this.getMemberIndex(member);

    Book book = this.members.get(memberIndex).getBookById(bookId);

    if (book != null) {
      this.jurnal.remove(book);
      this.books.add(book);
    }
    this.members.get(memberIndex).borrowedBooks.remove(book);
    } catch (NullPointerException e) {
      System.out.println("Id buku atau Id member invalid");
    }
    
  }

  private int getMemberIndex(Member member) {
    return this.members.indexOf(member);
  }

  private Member getMemberById(String memberId) {
    for (Member member : this.members) {
      if (member.getId().equals(memberId)) {
        return member;
      }
    }
    System.out.println("Terjadi kesalahan pada inputan  Id member");
    return null;
  }

  @Override
  public Book getBookById(String id) {
    for (Book book : this.books) {
      if (book.Id().equals(id)) {
        return book;
      }
    }
    System.out.println("Terjadi kesalahan pada inputan Id book");
    return null;
  }
}