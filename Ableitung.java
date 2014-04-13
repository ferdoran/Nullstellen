import java.util.Scanner;
/**
 * Klasse die ganzrationale Funktionen ableiten kann nach Kettenregel
 * Die Funktion muss nach folgendem Muster eingegeben werden
 * 3x^3-2x^2+1x^1+7x^0
 *
 * @author Roland
 *
 */
public class Ableitung {
	/**
	 * Definition des Scanners
	 */
	public static Scanner in;
	/**
	 * Leitet eine ganzrationale Funktion ab
	 * 
	 * @param p abzuleitende Funktion, Inhalt nach folgendem Muster: [zahl,exp,zahl,exp,...]
	 * @return gibt die abgeleitete Funktion zur�ck
	 */
	public static double[] ableiten(double[] p) {
		if(p.length > 2){
			double ableit[] = new double[p.length];
			for(int i=1;i < p.length;i++,i++) {
				ableit[i-1] = p[i-1]*p[i];
				ableit[i] = p[i] - 1.0;
				
				
			}
			return ableit;
		}
		
		else
			return new double[]{0};
	}
	/**
	 * Falls die abzuleitende Funktion ln(x) ist, soll 1/x zur�ckgegeben werden
	 * @return Ableitung von ln(x)
	 */
	public static String logAbleit() {
		return "1/x";
	}
	/**
	 * Wandelt einen String in ein double-Array um, um Ableiten zu erm�glichen.
	 * Die Speicherung erfolgt nach Muster [zahl1, exp1, zahl2, exp2, ...]
	 * @param s Umzuwandelnder String
	 * @return double-Array des umgewandelten Strings
	 */
	public static double[] strToDouble(String s) {
		int anzLeererStellen = 0;
		double converted[] = new double[s.length()];
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == 'x' || s.charAt(i) == '^' || s.charAt(i) == '+') {
				continue;
			}
			else if(s.charAt(i)-48 > 0.0)
				converted[i] = (double) s.charAt(i)-48;
			else if(s.charAt(i) == '-') {
				i += 1;
				converted[i-1] = -1 * (double) (s.charAt(i)-48) ;
				
			}
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
		converted = reduceCapacity(converted);
		return converted;
	}
	/**
	 * Sucht einen Wert innerhalb eines double-Arrays
	 * @param a Array in dem ein Wert gesucht werden soll
	 * @param val Zu suchender Wert
	 * @return Index an der sich der Wert im Array befindet
	 */
	public int searchVal(double[] a, double val) {
		for(int i=0;i < a.length; i++) {
			if(a[i] == val)
				return i;
			
		}
		return -1;
	}
	/**
	 * Gibt ein double-Array nach Muster [1.00, 2.00, 3.00] aus
	 * @param a Auszugebendes Array
	 */
	public static void arrayAusgeben(double[] a) {
		System.out.print("[");
		for(int i=0;i<a.length; i++) {
			System.out.printf("%f", a[i]);
			if(i < a.length-1)
				System.out.print(", ");
		}
		System.out.printf("]%n");
	}
		
	
	/**
	 * Wandelt eine ganzrationale Funktion, gespeichert in einem double-Array nach Muster
	 * [zahl1, exp1, zahl2, exp2]
	 * in einen String um
	 * @param p Umzuwandelndes Array
	 * @return Umgewandeltes Array
	 */
	public static String ganzRatToString (double[] p) {
		String ergebnis="";
	
		for(int i=1; i < p.length; i++, i++) {
			if(p[i] == 1.0) {
				ergebnis += " + " + p[i-1] + " x";
				continue;
			}
			else if(p[i] == 0.0) {
				ergebnis += " + " + p[i-1];
				continue;
			}
			ergebnis += " + " + p[i-1] + " x^" + (int)(p[i]);
		}
		return ergebnis;
	}
	/**
	 * Verringert ein Array auf die tats�chlich verwendete Gr��e
	 * @param a Zu verringerndes Array
	 * @return verrtingertes Array
	 */
	private static double[] reduceCapacity(double[] a) {
		int size = 0;
		for(int i=0;i < a.length; i++) {
			if(a[i] != 0.0)
				size++;
		}
		double reduced[] = new double[size];
		for(int i=0;i < size; i++) {
			reduced[i] = a[i];
		}
		return reduced;
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
