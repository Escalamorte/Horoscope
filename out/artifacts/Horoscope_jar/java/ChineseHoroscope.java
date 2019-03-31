import java.util.InputMismatchException;

class ChineseHoroscope {
    void getSing(int year) {
        try {
            while (year >= 1912) {
                year -= 12;
            }

            switch (year - 1899) {
                case 1:
                    System.out.println("Rat");
                    break;
                case 2:
                    System.out.println("Ox");
                    break;
                case 3:
                    System.out.println("Tiger");
                    break;
                case 4:
                    System.out.println("Rabbit ");
                    break;
                case 5:
                    System.out.println("Dragon ");
                    break;
                case 6:
                    System.out.println("Snake");
                    break;
                case 7:
                    System.out.println("Horse");
                    break;
                case 8:
                    System.out.println("Goat");
                    break;
                case 9:
                    System.out.println("Monkey");
                    break;
                case 10:
                    System.out.println("Rooster");
                    break;
                case 11:
                    System.out.println("Dog");
                    break;
                case 12:
                    System.out.println("Pig");
                    break;
                default:
                    System.out.println("Hmm... Something went wrong...");
                    System.out.println("Check your birth year. It must be greater or equal 1900");
                    break;
            }
        } catch (InputMismatchException exp) {
            System.out.println(exp);
        }
    }
}

