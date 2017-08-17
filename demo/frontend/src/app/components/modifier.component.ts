import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';


@Component({
  moduleId: module.id,
  selector: 'modifier',
  templateUrl: './modifier.component.html',
  providers:[GetsService]
 
})
export class Modifier {
  n  : number;
  ok : boolean;

produits :produit;
designation : string;
prix : number;
quantite: number;


constructor(private getsService:GetsService)  {
  this.ok=false;
}


editProduit()
{
this.getsService.getProduit(this.n)
              .subscribe ( posts =>
              {
                
                this.produits=posts;
                this.designation=this.produits.designation;
                this.prix=this.produits.prix;
                this.quantite=this.produits.quantite;

                
              });
}



validerEditProduit()
{
  this.getsService.modifierProduit(this.n,this.designation,this.prix,this.quantite)
            .subscribe ( posts =>
              {

                  this.ok=true;


                })



}

}


 


interface produit
 {
     id:number;
     designation:string;
     prix:number;
     quantite:number;


 }