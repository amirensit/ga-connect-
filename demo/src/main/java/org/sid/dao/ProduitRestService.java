package org.sid.dao;

import java.util.List;

import javax.validation.Valid;

import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitRestService {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	
	@RequestMapping(value="/produits",method=RequestMethod.GET)
	public List<Produit> listProduits()
	{
		return produitRepository.findAll(); // retourner en format JSON
	}
	
	
	
	@RequestMapping(value="chercherProduits",method=RequestMethod.GET)
	public Page<Produit> chercher(
			 String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		return produitRepository.chercherProduits("%"+mc+"%", new PageRequest(page, size)) ;
	}
	
	@RequestMapping(value="/produits",method=RequestMethod.POST) //pour dire lire les données dans la requête et que les données sont en format JSON,on utilise @requestBody 
	public Produit save(@RequestBody @Valid Produit p)
	{
		
		return produitRepository.save(p);
	}
	
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.PUT) 
	public Produit update(@RequestBody   Produit p,@PathVariable Long id)
	{
		p.setId(id);  // ??????
		
		return produitRepository.saveAndFlush(p);
	}
	
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.DELETE) //pour faire la suppression
	public void delete(@PathVariable Long id)
	{
		
		 produitRepository.delete(id);
	}
	
	/*@RequestMapping(value="/produits",method=RequestMethod.GET)
	public Page<Produit> pageProduits(int page,int size)
	{
		return produitRepository.findAll(new PageRequest(page, size)); 
	}*/
	
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.GET)
	public Produit getProduit(@PathVariable("id")  Long id)
	{
		return produitRepository.findOne(id); 
	}
	
	
	/*@RequestMapping(value="/produits/{nom}",method=RequestMethod.GET)
	public Produit prodaaa(@PathVariable("nom") String nom)
	{
		return produitRepository.findByName(nom);
	}*/
	
	
	
	
	
}
