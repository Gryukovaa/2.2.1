package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car logan = new Car("Logan", 10000);
      Car lancer = new Car("Lancer", 20000);
      Car lanos = new Car("Lanos", 30000);
      Car vesta = new Car("Vesta", 40000);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", logan));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", lancer));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", lanos));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", vesta));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      int carId = 40000;
      System.out.println("_________________________________");
      System.out.println("Car's owner is: " + userService.getUserByCar(carId).getFirstName() + " " + userService.getUserByCar(carId).getLastName());
      System.out.println("_________________________________");
      context.close();
   }
}
