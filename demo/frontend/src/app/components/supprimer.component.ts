import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';


@Component({
  moduleId: module.id,
  selector: 'delete',
  templateUrl: './supprimer.component.html',
  providers:[GetsService]
 
})
export class Supprimer {
id : number
a : boolean;
b: boolean;

constructor(private getsService:GetsService)
{
this.a=false;
this.b=false;
    
}





deleteProduct()
{

  this.getsService.supprimerProduit(this.id)
              .subscribe ( posts =>
              {
                this.a=true;
                
              }), error => {

              this.b=true;}
}
 

}