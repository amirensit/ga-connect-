import { Component , Input } from '@angular/core';

import { Router } from "@angular/router";
import {TypeServiceService} from '../../services/typeService.service';
import {Model} from '../../model/model';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'typeService.component.html',
    providers: [TypeServiceService],
})

export class TypeServiceComponent{
 n : any ;
 nom_typeService : string;
 nom_typeServiceForAjout : string;
typeServices : TypeService[];
filtre :Model = new Model();
partieAffiche : boolean;
partieAjout : boolean;

motCle:string="";

pageMarques : any;
pageCourante:number=0;
size:number=4;
pages:any[];


constructor(private _flashMessagesService: FlashMessagesService, private typeServiceService  : TypeServiceService,private route :Router){ }

ngOnInit(){
     this.typeServiceService.getAllTypeService()
         .subscribe ( posts =>
              {
                this.typeServices = posts;
                
              }
            );
      this.partieAffiche=true;
      this.partieAjout=false;
      //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 });
      

    
    }

     afficherPartieListe()
    {
        this.partieAffiche=true;
        this.partieAjout=false;
    }
    afficherPartieAjoutMarque()
    {
        this.partieAffiche=false;
        this.partieAjout=true;
    }

    recupererIdTypeService(a : number)
    {
        this.n=a;
    }

    editTypeService(a)
    {

        this.recupererIdTypeService(a);
        this.typeServiceService.getTypeService(a)
              .subscribe ( posts =>
              {
                this.nom_typeService=posts.nom_typeService;
             });

    }

    validerSupprimerTypeService(a : number)
    {
        this.typeServiceService.supprimerTypeService(a).subscribe(posts=>
                  { })
                  //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 });
                  window.location.reload();

    }

    validerEditTypeService()
    {
        this.typeServiceService.modifierTypeService(this.n,this.nom_typeService )
            .subscribe ( posts =>
              {
                 })
                 window.location.reload();
    }

    
    ajouterTypeService()
    {
        this.typeServiceService.ajouterTypeService(this.nom_typeServiceForAjout )
            .subscribe( posts =>
              {
                 })
    }

  

   

   
    

  




}

interface TypeService {
    id_typeService : number;
    nom_typeService : string;
}