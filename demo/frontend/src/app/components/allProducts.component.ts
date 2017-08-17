import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';


@Component({
  moduleId: module.id,
  selector: 'allProducts',
  templateUrl: './allProducts.component.html',
  providers:[GetsService]
 
})
export class AllProducts {

n:any;
designation : string;
prix : number;

quantite: number;

prod:produit;
  
showProducts:boolean;
show:string;
produits : produit[];

constructor(private getsService:GetsService)
{
    this.show="show";

    this.getsService.getAllProduits()
              .subscribe ( posts =>
              {
                this.produits = posts;
                
              });


}

aaa(a)
{
this.n=a;
}

showAllProducts()
{

    if(this.showProducts == true)
                {
               this.showProducts=false;
                this.show='show';
                 }
                else
                {
                  this.showProducts=true;
                this.show="hide";
                }
}


validerSupprimerProduit(b)
{

    this.getsService.getAllProduits()
              .subscribe ( vv =>
              {
                  this.produits = vv;
                
                  this.getsService.supprimerProduit(b).subscribe(posts=>
                  {
                      
                  })
              });

  

}


validerEditProduit()
{
    
  this.getsService.modifierProduit(this.n,this.designation,this.prix,this.quantite)
            .subscribe ( posts =>
              {
                 })



}



editProduit(a)
{
    this.aaa(a);
    this.getsService.getProduit(a)
              .subscribe ( posts =>
              {
                
                this.prod=posts;
                this.designation=this.prod.designation;
                this.prix=this.prod.prix;
                this.quantite=this.prod.quantite;

                
              });
}




}


interface produit
 {
     id:number;
     designation:string;
     prix:number;
     quantite:number;


 }

 

