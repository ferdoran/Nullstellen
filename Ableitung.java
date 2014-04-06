package util;

import java.util.Scanner;

public class Ableitung {
	public static Scanner in;
	
	public static double[] ableiten(double[] p) {
		if(p.length > 2){
			double ableit[] = new double[p.length];
			for(int i=1;i < p.length;i++,i++) {
				ableit[i-1] = p[i-1]*p[i];
				ableit[i] = p[i] - 1.0;
				
				
			}
			return ableit;		}
		else
			return new double[]{0};
	}
	public static String logAbleit() {
		return "1/x";
	}
	
	public static double[] strToDouble(String s) {
		int anzLeererStellen = 0;
		double converted[] = new double[s.length()];
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == 'x' || s.charAt(i) == '^') {
				continue;
			}
			else
				converted[i] = (double) s.charAt(i)-48;
		}
		for(int i=1;i < converted.length; i++) {
			if(converted[i] == 0.0) {
				anzLeererStellen +=1;
				continue;
			}
			else {
				converted[i-anzLeererStellen] = converted[i];
				converted[i] = 0.0;
			}
		}

		return converted;
	}
	
	public int searchVal(double[] a, double val) {
		for(int i=0;i < a.length; i++) {
			if(a[i] == val)
				return i;
			
		}
		return -1;
	}
	
	public static void arrayAusgeben(double[] a) {
		System.out.print("[");
		for(int i=0;i<a.length; i++) {
			System.out.printf("%f", a[i]);
			if(i < a.length-1)
				System.out.print(", ");
		}
		System.out.printf("]%n");
	}
		
	
	
	public static String ganzRatToString (double[] p) {
		String ergebnis="";
	
		for(int i=1; i < p.length; i++, i++) {
			ergebnis += " + " + p[i-1] + " x^" + (int)(p[i]);
		}
		return ergebnis;
	}
	
	public static void main(String args[]) {
		in = new Scanner(System.in);
		String fkt = in.nextLine();
		
		System.out.println("Funktion vor Ableiten: " + fkt);
		System.out.print("Umwandlung von String in Double-Array: ");
		arrayAusgeben(strToDouble(fkt));
		fkt = ganzRatToString(ableiten(strToDouble(fkt)));
		System.out.println("Funktion nach Ableiten: " + fkt);
	}
}
