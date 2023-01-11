import java.util.Scanner;

class Fahrkartenautomat {
	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);

		double zuZahlenderBetrag;
		double minZuZahlenderBetrag;
		double eingezahlterGesamtbetrag;
		double eingeworfeneMuenze;
		double rueckgabebetrag;
		double nochZuZahlen;
		int tickets;
		int maxTickets;

		// Geldbetrag eingeben
		System.out.print("Zu zahlender Betrag (Euro): ");
		zuZahlenderBetrag = tastatur.nextDouble();
		System.out.print("Anzahl der Tickets:");
		tickets = tastatur.nextInt();

		// Geldbetrag eingeben
		eingezahlterGesamtbetrag = 0.0;
		nochZuZahlen = 0.0;
		maxTickets = 10;
		minZuZahlenderBetrag = 1;
		
		if (tickets > maxTickets) { tickets = maxTickets; System.out.println("Fehlerhafte Eingabe -> Ticketanzahl wird auf " + maxTickets + " gesetzt!"); }
		if (zuZahlenderBetrag <= 0) { zuZahlenderBetrag = minZuZahlenderBetrag; System.out.println("Fehlerhafte Eingabe -> Ticketpreis wird auf " + minZuZahlenderBetrag + " gesetzt!"); }
		zuZahlenderBetrag = tickets * zuZahlenderBetrag;
		
		while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
			nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
			System.out.print("Noch zu zahlen: ");
			System.out.printf("%.2f", nochZuZahlen);
			System.out.print(" Euro\n");
			System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
			eingeworfeneMuenze = tastatur.nextDouble();
			eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
		}
		
		// Fahrscheinausgabe
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(200);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
		
		// Rückgeldberechung und -ausgabe
		
		rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
		System.out.print(rueckgabebetrag);
		rueckgabebetrag = Math.round(rueckgabebetrag * 10) / 10;
		System.out.print("\n" + rueckgabebetrag);
		if (rueckgabebetrag > 0) {
			System.out.println("Der Rückgabebetrag in Höhe von " + rueckgabebetrag + " Euro");
			System.out.println("wird in folgenden Münzen ausgezahlt:");
			while (rueckgabebetrag >= 2) { // 2-Euro-Münzen
				System.out.println("2 Euro");
				rueckgabebetrag = rueckgabebetrag - 2.0;
			}
			while (rueckgabebetrag >= 1) { // 1-Euro-Münzen
				System.out.println("1 Euro");
				rueckgabebetrag = rueckgabebetrag - 1.0;
			}
			while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
				System.out.println("50 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.5;
			}
			while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
				System.out.println("20 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.2;
			}
			while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
				System.out.println("10 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.1;
			}
			while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
				System.out.println("5 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.05;
				System.out.println(rueckgabebetrag);
			}
		}
		
		if (tickets > 1) {
			System.out.print("\nVergessen sie nicht, die Fahrscheine\n");
		} else {
			System.out.print("Vergessen Sie nicht, den Fahrschein\n");
		}
		System.out.print("vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");

		tastatur.close();
		
	}
}