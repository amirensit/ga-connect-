import { Component , Input,OnInit } from '@angular/core';

import { Router } from "@angular/router";
import {RemorqueurService} from "../../services/remorqueur.service";
import {VilleService} from "../../services/ville.service";
import {GouvernoratService} from "../../services/gouvernorat.service";
import {RemorqueurModel} from "../../model/remorqueur"




@Component({
    selector: 'maps-cmp',
    moduleId: module.id,
    templateUrl: 'remorqueur.component.html',
    providers: [RemorqueurService,VilleService,GouvernoratService],
})

export class RemorqueurComponent implements OnInit{
   
remorqueurs : Remorqueur[];
gouvernorats : Gouvernorat[];
gForListVille : Gouvernorat ;
matricule_fiscal : string;
nom_contact : string;
prenom_contact : string;
etat : boolean;
raison_social : string;
tel ;
n : any;
partieAffiche : boolean;
partieAjout : boolean;
matricule_fiscalForAjout : string
nom_contactForAjout : string;
prenom_contactForAjout : string;
etatForAjout : boolean;
villeForAjout : Ville;
villeForEdit : Ville;
villes : Ville[];
raison_socialForAjout : string;
telForAjout : string;
id_villeForModification : number;
bloquerOk;
debloquerOk;


 constructor(private remorqueurService : RemorqueurService,private gouvernoratService : GouvernoratService,private villeService : VilleService,private route :Router){ }
   
    ngOnInit(){
     
     this.remorqueurService.getAllRemorqueur()
         .subscribe ( posts =>
              {
                this.remorqueurs = posts;
              }
            );

            this.gouvernoratService.getListGouvernorat().subscribe(posts =>
              {
                this.gouvernorats = posts;
              });

            this.partieAffiche=true;
            this.partieAjout=false;
            

    
       
       
    }

   

    afficherPartieListe()
    {
        this.partieAffiche=true;
        this.partieAjout=false;
    }
    afficherPartieAjoutRemorqueur()
    {
        this.partieAffiche=false;
        this.partieAjout=true;
    }
    

    recupererIdRemorqueur(a : number)
    {
        this.n=a;
    }

    validerSupprimerRemorqueur(b : number)
    {
        this.remorqueurService.supprimerRemorqueur(b).subscribe(posts=>
                  { })
                  window.location.reload();
    }

    editRemorqueur(a)
    {
    this.recupererIdRemorqueur(a);
    this.remorqueurService.getRemorqueur(a)
              .subscribe ( posts =>
              {
               
                this.matricule_fiscal=posts.matricule_fiscal;
                this.nom_contact=posts.nom_contact;
                this.etat=posts.etat;
                this.prenom_contact=posts.prenom_contact;
                this.raison_social=posts.raison_social;
                this.tel=posts.tel;
                this.id_villeForModification=posts.villeDTO.id_ville;
                
                });
    }

validerEditRemorqueur()
{
   this.remorqueurService.modifierRemorqueur(this.n ,this.matricule_fiscal  , this.nom_contact  , this.prenom_contact  , this.etat ,
   this.villeForEdit.id_ville ,this.raison_social  ,this.tel)
    .subscribe ( posts =>
              {
                 })
                 window.location.reload();


}



getVilleByGouvernorat()
{
    this.villeService.getVilleByGouvernorat(this.gForListVille.id_gouvernorat).subscribe(posts =>
              {
                this.villes = posts;
              });

}



ajouterRemorqueur()
{
   
    
    this.remorqueurService.ajouterRemorqueur(this.matricule_fiscalForAjout ,this.nom_contactForAjout 
    ,this.prenom_contactForAjout ,this.etatForAjout 
    ,this.villeForAjout.id_ville,this.raison_socialForAjout
    ,this.telForAjout)
    .subscribe(posts=>
                  { });
                 

}




bloquerRemorqueur(isChecked: boolean)
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
     console.log(this.etatForAjout);
}


debloquerRemorqueur(isChecked: boolean)
{
    this.bloquerOk=false;
    this.debloquerOk=true;
    if(isChecked) {
    this.etat=true;
  } else {
    this.etat=false;

   
  }
   console.log(this.etat);
}






 
}


interface Remorqueur
{
    id_remorqueur : number;
    matricule_fiscal : string;
    nom_contact : string;
    prenom_contact : string;
    etat : boolean;
}

interface Gouvernorat {
    id_gouvernorat : number;
    nom_gouvernorat : string;
}


   
interface Ville 
{
    id_ville : number;
    nom_ville : string;
    gouvernorat : Gouvernorat;
}
    



