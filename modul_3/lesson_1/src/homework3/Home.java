package homework3;

import homework3.exceptions.ColorCantBeBlankException;
import homework3.exceptions.FlourCantBeLessThanZeroException;
import homework3.exceptions.PersonNameCantBeBlankException;
import homework3.exceptions.RoomCountCantBeLessThanTenException;

public class Home {
  private Integer flour;
  private  String color;
  private  Integer roomCount;
  private String personName;

  @Override
  public String toString() {
    return "Home{" +
        "flour=" + flour +
        ", color='" + color + '\'' +
        ", roomCount=" + roomCount +
        ", personName='" + personName + '\'' +
        '}';
  }

  public Home() {
  }

  public Integer getFlour() {
    return flour;
  }

  public void setFlour(Integer flour) {
    try {
      if (flour > 0) {
        this.flour = flour;
      }else {
        throw new FlourCantBeLessThanZeroException("Qavat 0dan katta bolishi kerak");
      }
    }catch( FlourCantBeLessThanZeroException e ){
      System.out.println(e.getMessage());

    }

  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    try {
      if (color.isEmpty()) {
        throw new ColorCantBeBlankException("Rang kiritilishi kerak!");
      }else {
        this.color = color;
      }
    }catch(ColorCantBeBlankException e){
      System.out.println(e.getMessage());
    }
  }

  public Integer getRoomCount() {
    return roomCount;
  }

  public void setRoomCount(Integer roomCount) {
    try {
      if (roomCount > 10) {
        this.roomCount = roomCount;
      }else {
        throw new RoomCountCantBeLessThanTenException("10dan katta kiritilishi kerak!");
      }
    }catch(RoomCountCantBeLessThanTenException e){
      System.out.println(e.getMessage());
    }
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    try {
      if (!personName.isEmpty()) {
        this.personName = personName;
      }else {
        throw new PersonNameCantBeBlankException("Ism  kiritilishi kerak!");
      }
    }catch(PersonNameCantBeBlankException e){
      System.out.println(e.getMessage());
    }
  }
}
