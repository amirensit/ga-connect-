import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';
import initDemo = require('../../../assets/js/charts.js');
import {PackService} from '../../services/pack.service';

import { Router } from "@angular/router";
import {PackModel} from '../../model/pack';

@Component({
    selector: 'home-cmp',
    moduleId: module.id,
    templateUrl: 'pack.component.html',
     providers: [PackService],
})

export class PackComponent implements OnInit{

    
n : any;
partieAffiche : boolean;
partieAjout : boolean;
packs : Pack[];
pedit : Pack[];
nom_pack : string;
description : string;
nbre_max_service : number;
kilometrage_max : number;
etat : boolean;
bloquerOk;
debloquerOk;


nom_packForAjout : string;
descriptionForAjout : string;
nbre_max_serviceForAjout : number;
kilometrage_maxForAjout : number;
etatForAjout : boolean;

filtre :PackModel = new PackModel();


     constructor(private  packService: PackService,private route :Router){}
   
    ngOnInit(){

        this.packService.getAllPack().subscribe( posts => {this.packs = posts;});
            this.partieAffiche=true;
            this.partieAjout=false;
        
       
    }


    afficherPartieListe()
    {
        this.partieAffiche=true;
        this.partieAjout=false;
    }
    afficherPartieAjoutPack()
    {
        this.partieAffiche=false;
        this.partieAjout=true;
    }

    recupererIdPack(a : number)
    {
        this.n=a;
    }

    editPack(a)
    {
        this.recupererIdPack(a);
        this.packService.getPack(a)
              .subscribe ( posts =>
              {
                this.nom_pack=posts.nom_pack;
                this.description=posts.description;
                this.nbre_max_service=posts.nbre_max_service;
                this.kilometrage_max=posts.kilometrage_max;
                this.etat=posts.etat;
             
                

                
              });
    }

    validerSupprimerPack(a)
    {
        this.packService.supprimerPack(a).subscribe(posts=>
                  { })
                  window.location.reload();

    }

    

    validerEditPack()
    {
        this.packService.modifierPack(this.n,this.nom_pack ,this.description, this.nbre_max_service,this.kilometrage_max , this.etat)
            .subscribe ( posts =>
              {
                 })
                 window.location.reload();
    }

    ajouterPack()
    {
        this.etatForAjout=true;
        this.packService.ajouterPack(this.nom_packForAjout , this.descriptionForAjout , this.nbre_max_serviceForAjout  , this.kilometrage_maxForAjout ,this.etatForAjout)
            .subscribe( posts =>
              {
                 })
    }



bloquerPack(isChecked: boolean)
{
    this.bloquerOk=true;
    this.debloquerOk=false;
    if(isChecked) {
    this.etat=false;
    this.etatForAjout=false;

  } else {
    this.etat=true;
    this.etatForAjout=true;

   
  }
   console.log(this.etat);
}


debloquerPack(isChecked: boolean)
{
    this.bloquerOk=false;
    this.debloquerOk=true;
    if(isChecked) {
    this.etat=true;
    this.etatForAjout=true;
  } else {
    this.etat=false;
    this.etatForAjout=false;

   
  }
   console.log(this.etat);
}


}

interface Pack{
    id_pack : number;
    nom_pack : string;
    description : string;
    nbre_max_service : number;
    kilometrage_max : number;
    etat : string;
}
