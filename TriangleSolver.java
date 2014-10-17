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
		Graphics(triangle);
		triangle = Input(triangle);
	}
	
	static void Graphics(double triangle[]) {

		Println("Plain + Simple Triangle Solver Java edition");
		Println("                       B");
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
		Println("        A     Side B    C\n");
	}
	static void Println(String s) {
		System.out.println(s);
	}
	static void Print(String s) {
		System.out.print(s);
	}
	static double[] Input(double[] triangle) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			Print("Enter side " + (char)(i+'A') + " (0 if unknown): ");
			triangle[i] = input.nextInt();
		}
		for (int i = 0; i < 3; i++) {
			Print("Enter angle " + (char)(i+'a') + " (0 if unknown): ");
			triangle[2 * i] = input.nextInt();
		}
		input.close();
		return triangle;
	}
}
