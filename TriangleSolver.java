/*******************************************************************************
* TriangleSolver.java
*
* Description: This is the main class for the Java triangle solver, which solves
* missing sides and angles on a triangle using trigonometry.
*
* By Derik Kauffman, Matthew McMillan, Stefan Kussmaul
*******************************************************************************/
import java.util.Scanner;

public class TriangleSolver {

  public static void main(String[] args) {
    double[] triangle = new double[6];
    Graphics();
    triangle = Input(triangle);
    Println("The triangle is " + GetAngleType(triangle) + " and " + GetTriangleType(triangle) + ".");
  }
  
  static void Graphics() {

    Println("Plain + Simple Triangle Solver Java edition");
    Println("                       a");
    Println("                       .");
    Println("                      /|");
    Println("                     / |");
    Println("                    /  |");
    Println("                   /   |");
    Println("                  /    |");
    Println("                 /     |");
    Println("         Side A /      | Side B");
    Println("               /       |");
    Println("              /        |");
    Println("             /         |");
    Println("            /          |");
    Println("           /           |");
    Println("          /____________|");
    Println("        c     Side C    b\n");
  }
  
  static void Println(String s) {
    System.out.println(s);
  }
  static void Print(String s) {
    System.out.print(s);
  }
  
  static double[] Input(double[] triangle) {
    Scanner input = new Scanner(System.in);
    /* Sides are odd */
    for (int i = 0; i < 3; i++) {
      Print("Enter side " + (char)(i+'A') + " (0 if unknown): ");
      triangle[2 * i + 1] = input.nextInt();
    }
    /* Angles are even */
    for (int i = 0; i < 3; i++) {
      Print("Enter angle " + (char)(i+'a') + " (0 if unknown): ");
      triangle[2 * i] = input.nextInt();
    }
    
    input.close();
    return triangle;
  }
  static String GetTriangleType(double triangle[]) {
    if ((triangle[0] == triangle [2]) || (triangle[0] == triangle[4]) || (triangle[2] == triangle[4])) {
      if((triangle[0] == triangle [2]) && (triangle[0] == triangle[4]) && (triangle[2] == triangle[4]))
        return "equilateral";
      return "isosceles";
    }
  return "scalene";
  }
  static String GetAngleType(double triangle[]) {
    for (int i = 0; i < 3; i++) {
      if (triangle[2 * i] == 90) {
        return "right";
      }
      if (triangle[2 * i] > 90) {
        return "obtuse";
      }
    }
    /* If no right or obtuse angles were found, it must be an acute triangle */
    return "acute";
  }
}
