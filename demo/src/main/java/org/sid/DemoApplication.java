package org.sid;

import java.util.List;

import org.sid.dao.ProduitRepository;

import org.sid.entities.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{
	@Autowired
	private ProduitRepository produitRepository;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

	@Override
	public void run(String... arg0) throws Exception {
		
		
		//produitRepository.save(new Produit("Lx 568",900,23));
		
		//produitRepository.save(new Produit("HP 54",100,8));
		//produitRepository.save(new Produit("ht 1234 ",90,3));
		List<Produit> prods=produitRepository.findAll();// permet de retrouner tous les produits
		prods.forEach(p->System.out.println(p.getDesignation())  );
		
		
	}
	
	
}
