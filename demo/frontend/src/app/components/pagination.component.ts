import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';
import{AuthGuard} from '../guard/auth.guard';


@Component({
  moduleId: module.id,
  selector: 'pagination',
  templateUrl: './pagination.component.html',
  providers:[GetsService,AuthGuard]
 
})
export class Pagination {

pageProduits;
motCle:string="";
pageCourante:number=0;
size:number=4;
pages:any[];


constructor(private getsService:GetsService)
{

    
}


chercherProduits()
{
    this.getsService.serviceFindProduct(this.motCle,this.pageCourante,this.size)
    .subscribe  (response =>
              {
                
            this.pageProduits=response;
            this.pages=new Array(response.totalPages);

                
              });
}



getProduits()
{
    this.pageCourante=0;
    this.chercherProduits();
}



goToPage(p)
{
this.pageCourante=p;
this.chercherProduits();


}




}
 

