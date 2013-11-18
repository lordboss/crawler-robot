package com.brahim.sid;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	/**
	 * @param args
	 * @Autor Brahim 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int nbpage;
	    //	String Query;
		// http://fr.espacenet.com/searchResults?page=1&ST=singleline&compact=false&query=green+and+computing&locale=fr_FR&DB=fr.espacenet.com
		Document doc, doc2;// les deuc document pour le stokage des pages web
		for (int i = 1; i <= 3; i++) { //boucle sur les pages de resultat 
			try {

				// need http protocol 
				doc = Jsoup
						.connect(
								"http://fr.espacenet.com/searchResults?page="
										+ i
										+ "&ST=singleline&compact=false&query=green+and+computing&locale=fr_FR&DB=fr.espacenet.com")
						.get();

				// ici la methode pour prendere le contenu de la page i

											// String title = doc.title();
											// System.out.println("title : " + title);
											// get the value from href attribute
											// System.out.println("\nlink : " + link.attr("href"));
											// System.out.println("text : " + link.text());
				// get all links
				Elements links = doc.select("span a[href]");//prendre l'url de chaque article
				for (Element link : links) {//iterer sur les liens

					
				
					PrintWriter fichier; // cree un fichier pour stocker les resultats
					try {
						File fb = new File("result"); //dossier des resultats
						fb.mkdirs(); 
						
						fichier = new PrintWriter(new FileWriter("result./"+link.text()
								+ ".html"));

						try {

							doc2 = Jsoup.connect(
									"http://fr.espacenet.com"
											+ link.attr("href")).get();//connecter au site de l'article 
							Elements lin = doc2.select("#pagebody");//id de contenu de l'article
							fichier.print(lin.clone());//copier tous le conenu en html

						} catch (IOException e) {
							e.printStackTrace();
						}
						// fichier.print(link.attr("href"));
						fichier.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("fin");
		// fichier.write(link.text());

	}

}
