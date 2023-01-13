import java.util.Scanner;

class Fahrkartenautomat {
	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);

		double zuZahlenderBetrag = 0;
		double eingezahlterGesamtbetrag;

		int tickets = 0;

		// Begrüssung
		begruessung();

		// Geldbetrag eingeben
		zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);

		// Ticket Anzahl
		tickets = ticketAnzahlErfassen(tastatur);

		// Geldbetrag eingeben
		eingezahlterGesamtbetrag = eingezahlterGesamtbetrag(tastatur, zuZahlenderBetrag, tickets);

		// Fahrscheinausgabe
		fahrscheinAusgabenAnimation();

		// Rückgeldberechung und -ausgabe
		rueckgeldBerechnung(eingezahlterGesamtbetrag, zuZahlenderBetrag);

		// abschiedsNachricht
		abschiedsNachricht(tickets);

	}

	private static void begruessung() {
		System.out.println("Herzlich Willkommen!\n");
	}

	private static double fahrkartenbestellungErfassen(Scanner tastatur) {
		int ticketAuswahl = 0;
		double zuZahlenderBetrag = 0;
		
		String[] fahrkartenNamen = {
			"Einzelfahrschein AB",
			"Einzelfahrschein BC",
			"Einzelfahrschein ABC",
			"Kurzstrecke AB",
			"Tageskarte AB",
			"Tageskarte BC",
			"Tageskarte ABC",
			"4-Fahrten-Karte AB",
			"4-Fahrten-Karte BC",
			"4-Fahrten-Karte ABC",
			"Kleingruppen-Tageskarte AB",
			"Kleingruppen-Tageskarte BC",
			"Kleingruppen-Tageskarte ABC"
		};
		
		Double[] fahrkartenPreise = {
				3.00,
				3.50,
				3.80,
				2.00,
				8.60,
				9.20,
				10.00,
				9.40,
				12.60,
				13.80,
				25.50,
				26.00,
				26.50
		};
		
		while (zuZahlenderBetrag == 0) {
			System.out.print("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:\r\n");
			for (int i = 0; i < fahrkartenNamen.length; i++) {
				System.out.println("(" + i + ") -> " + fahrkartenNamen[i] + " | " + fahrkartenPreise[i] + "€");
			}
			
			System.out.print("ihre Wahl ->");
			ticketAuswahl = tastatur.nextInt();
			
			if (ticketAuswahl <= fahrkartenNamen.length && ticketAuswahl >= 0) {
				zuZahlenderBetrag = fahrkartenPreise[ticketAuswahl];
			}

		}
		
		return zuZahlenderBetrag;

	}

	private static int ticketAnzahlErfassen(Scanner tastatur) {
		int tickets = 0;

		while (tickets == 0) {
			System.out.println("Geben Sie ihre Wunschanzahl an Tickets ein (maximal: 10):");
			tickets = tastatur.nextInt();

			if (tickets > 10) {
				System.out.println(">> Fehlerhafte Eingabe <<");
				tickets = 0;
			}
		}
		return tickets;
	}

	private static double eingezahlterGesamtbetrag(Scanner tastatur, double zuZahlenderBetrag, int tickets) {
		double eingezahlterGesamtbetrag = 0.0;
		double nochZuZahlen = 0.0;
		int maxTickets = 10;
		int minZuZahlenderBetrag = 1;
		double eingeworfeneMuenze;

		if (tickets > maxTickets) {
			tickets = maxTickets;
			System.out.println("Fehlerhafte Eingabe -> Ticketanzahl wird auf " + maxTickets + " gesetzt!");
		}
		if (zuZahlenderBetrag <= 0) {
			zuZahlenderBetrag = minZuZahlenderBetrag;
			System.out.println("Fehlerhafte Eingabe -> Ticketpreis wird auf " + minZuZahlenderBetrag + " gesetzt!");
		}
		zuZahlenderBetrag *= tickets;

		while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
			nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
			System.out.print("Noch zu zahlen: ");
			System.out.printf("%.2f", nochZuZahlen);
			System.out.print(" Euro\n");

			eingeworfeneMuenze = 0;

			while (eingeworfeneMuenze != 0.1 && eingeworfeneMuenze != 0.2 && eingeworfeneMuenze != 0.5
					&& eingeworfeneMuenze != 1 && eingeworfeneMuenze != 2 && eingeworfeneMuenze != 5
					&& eingeworfeneMuenze != 10 && eingeworfeneMuenze != 20) {
				System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
				eingeworfeneMuenze = tastatur.nextDouble();

			}

			eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
		}

		return eingezahlterGesamtbetrag;
	}

	private static double rueckgeldBerechnung(double eingezahlterGesamtbetrag, double zuZahlenderBetrag) {
		double rueckgabebetrag;

		rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
		rueckgabebetrag = Math.round(rueckgabebetrag * 10) / 10;
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
		return rueckgabebetrag;
	}

	private static void fahrscheinAusgabenAnimation() {
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
	}

	private static void abschiedsNachricht(int tickets) {
		if (tickets > 1) {
			System.out.print("\nVergessen sie nicht, die Fahrscheine\n");
		} else {
			System.out.print("Vergessen Sie nicht, den Fahrschein\n");
		}
		System.out.print("vor Fahrtantritt entwerten zu lassen!\n" + "Wir wünschen Ihnen eine gute Fahrt.");
	}

}