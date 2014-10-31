/*******************************************************************************
* TriangleSolver.java
*
* Description: This is the main class for the Java triangle solver, which solves
* missing sides and angles on a triangle using trigonometry.
*
* By Derik Kauffman, Matthew McMillan, Stefan Kussmaul
*******************************************************************************/
import java.lang.Math.*;
import java.util.Scanner;

public class TriangleSolver {

  public static void main(String[] args) {
    double[] triangle = new double[6];
    Graphics();
    triangle = Input(triangle);
    triangle = Solve(triangle);
    Output(triangle);
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
    Println("         Side C /      | Side A");
    Println("               /       |");
    Println("              /        |");
    Println("             /         |");
    Println("            /          |");
    Println("           /           |");
    Println("          /____________|");
    Println("        c     Side B    b\n");
  }
  
  static void Println(String s) {
    System.out.println(s);
  }
  static void Print(String s) {
    System.out.print(s);
  }
  
  static double sin(double degrees) {
    return Math.sin(Math.toRadians(degrees));
  }
  static double cos(double degrees) {
    return Math.cos(Math.toRadians(degrees));
  }
  static double asin(double degrees) {
    return Math.asin(Math.toRadians(degrees));
  }
  static double acos(double degrees) {
    return Math.acos(Math.toRadians(degrees));
  }
  static double pow(double base, double exponent) {
    return Math.pow(base, exponent);
  }
  static double sqrt(double number) {
    return Math.sqrt(number);
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
      if ((triangle[0] == triangle [2]) && (triangle[0] == triangle[4]) && (triangle[2] == triangle[4]))
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
  
  static double[] SolveLastAngle(double triangle[]) {
    for (int i = 0; i < 6; i+=2) {
      if (triangle[i] == 0) {
        triangle[i] = 180 - (triangle[(i+2) % 6] + triangle[(i-2) % 6]);
        return triangle;
      }
    }
    return triangle;
  }
  
  static double GetSideToAngleRatio(double triangle[]) {
    for (int i = 0; i < 6; i += 2) {
      if (triangle[i] > 0 && triangle[(i + 3) % 6] > 0) {
        return triangle[(i + 3) % 6] / sin(triangle[i]);
      }
    }
    return 0;
  }
  
  static double GetAngleToSideRatio(double triangle[]) {
    for (int i = 0; i < 6; i += 2) {
      if (triangle[i] > 0 && triangle[(i + 3) % 6] > 0) {
        return sin(triangle[i]) / triangle[(i + 3) % 6];
      }
    }
    return 0;
  }
  
  static double[] SolveMissingSideLOS(double triangle[], int missing_side) {
    triangle[missing_side] =
    GetSideToAngleRatio(triangle) * sin(triangle[(missing_side + 3) % 6]);
    return triangle;
  }
  
  static double[] SolveMissingAngleLOS(double triangle[], int missing_angle) {
    triangle[missing_angle] =
    asin(GetAngleToSideRatio(triangle) * triangle[(missing_angle + 3) % 6]);
    return triangle;
  }
  
  static double[] SolveMissingSideLOC(double triangle[], int missing_side) {
    triangle[missing_side] = sqrt(
      pow(triangle[(missing_side + 2) % 6], 2) + /* a^2 */
      pow(triangle[(missing_side - 2) % 6], 2) - /* b^2 */
      (2 * triangle[(missing_side + 2) % 6] * triangle[(missing_side - 2) % 6] *
      cos(triangle[(missing_side + 3) % 6]))); /* 2ab*cosC */
    return triangle;
  }
  
  static double[] SolveMissingAngleLOC(double triangle[], int missing_angle) {
    triangle[missing_angle] =
    acos(
         /* a^2 + b^2 - c^2 */
         (pow(triangle[(missing_angle + 1) % 6], 2) +
          pow(triangle[(missing_angle + 5) % 6], 2) -
          pow(triangle[(missing_angle + 3) % 6], 2)) /
         /* 2ab */
         (2 * triangle[(missing_angle + 1) % 6] * triangle[(missing_angle + 5) % 6]));
    return triangle;
  }
  
  static double[] Solve(double triangle[]) {
    for (int i = 0; i < 6; i += 2) {
      /* if 2 angles are solved but angle i is not */
      if (triangle[i] == 0 && triangle[(i + 2) % 6] > 0 && triangle[(i - 2) % 6] > 0) {
        triangle = SolveLastAngle(triangle);
        i = 0;
        /* if all sides are known, but angle i isn't */
      } else if (triangle[1] > 0 && triangle[3] > 0 && triangle[5] > 0 && triangle[i] == 0) {
        triangle = SolveMissingAngleLOC(triangle, i);
        i = 0;
      } else if (triangle[(i - 1) % 6] > 0 && triangle[(i - 2) % 6] > 0 &&
                 triangle[(i - 3) % 6] > 0 && triangle[(i + 1) % 6] == 0) {
        triangle = SolveMissingSideLOC(triangle, (i + 1));
        i = 0;
      } else if (((triangle[i + 1] > 0 && triangle[(i + 4) % 6] > 0) ||
                  (triangle[(i - 1) % 6] > 0 && triangle[(i - 4) % 6] > 0)) &&
                 triangle[i] == 0) {
        triangle = SolveMissingAngleLOS(triangle, i);
        i = 0;
      } else if (((triangle[(i + 2) % 6] > 0 && triangle[(i + 5) % 6] > 0) ||
                  (triangle[i] > 0 && triangle[(i - 3) % 6] > 0)) &&
                 triangle[i + 1] == 0) {
        triangle = SolveMissingSideLOS(triangle, (i + 1));
        i = 0;
      }
    }
    return triangle;
  }
  
  static void Output(double triangle[]) {
    Println("The triangle is " + GetTriangleType(triangle) + " and " + GetAngleType(triangle) + ".");
    Println("Side A:\t " + triangle[1]);
    Println("Side B:\t " + triangle[3]);
    Println("Side C:\t " + triangle[5]);
    Println("\nAngle a:\t " + triangle[0]);
    Println("Angle b:\t " + triangle[2]);
    Println("Angle c:\t " + triangle[4]);
  }
}
