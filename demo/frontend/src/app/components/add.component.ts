import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';
import "rxjs/add/operator/catch";


@Component({
  moduleId: module.id,
  selector: 'add',
  templateUrl: './add.component.html',
  
  
  providers:[GetsService]
 
})
export class Add {

des :string;
prix: number;
quantite : number;
ok :boolean;
no : boolean;
error:boolean;


constructor(private getsService:GetsService)
{
  this.ok=false;
    
}

addProduct()
{

  this.getsService.ajouterProduit(this.des,this.prix,this.quantite)
              .subscribe ( posts =>
              {
                this.ok=true
                
              }), error => {

              this.no=true;}

  
}

onSubmit(value : any)
{
  console.log(value);
}



}
 

