// overload polymorphism

class Book {
  private String id;
  private String title;

  public void Id(String id) {
    this.id = id;
  }

  public String Id() {
    return id;
  }

  public void Title(String title) {
    this.title = title;
  }

  public String Title() {
    return title;
  }
}