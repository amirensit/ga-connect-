import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';
import initDemo = require('../../../assets/js/charts.js');
import {ContratService } from '../../services/contrat.service';
import {VilleService} from "../../services/ville.service";
import {GouvernoratService} from "../../services/gouvernorat.service";

import { Router } from "@angular/router";





@Component({
   
    moduleId: module.id,
    templateUrl: 'contrat.component.html',
     providers: [ContratService,VilleService,GouvernoratService],
})

export class ContratComponent implements OnInit{

    contrats;
    dateInvalid : boolean;
    gouvernorats : Gouvernorat[];
    packs : Pack[];
    marques : Marque[];
    gForListVille : Gouvernorat ;
    villes : Ville[];
    villeForEdit;
    packForEdit;
    marqueForEdit;
    villeForAjout;
    marqueForAjout;
    packForAjout;
    partieAffiche : boolean;
    partieAjout : boolean;
    partieImport : boolean;
    n : any;
    addresse;
    addresseForAjout;
    date_debut;
    date_debutForAjout;
    date_fin;
    date_finForAjout;
    nom;
    nomForAjout;
    num_contrat;
    num_contratForAjout;
    prenom;
    prenomForAjout;
    import;
    

    constructor(private  contratService: ContratService,private gouvernoratService : GouvernoratService,private villeService : VilleService,private route :Router){ }

    ngOnInit(){
        
        
     this.dateInvalid=false;
     this.contratService.getAllContrat()
         .subscribe ( posts =>
              {
                this.contrats = posts;
                console.log(this.contrats);
              }
            );

    this.contratService.getAllPack().subscribe( posts => {this.packs = posts;});
    this.contratService.getAllMarque().subscribe( posts => {this.marques = posts;})

     this.gouvernoratService.getListGouvernorat().subscribe(posts =>
              {
                this.gouvernorats = posts;
              });

               this.partieAffiche=true;
            this.partieAjout=false;
            this.partieImport=false;
  
}

afficherPartieListe()
    {
        this.partieAffiche=true;
        this.partieAjout=false;
         this.partieImport=false;
    }
    afficherPartieAjoutContrat()
    {
        this.partieAffiche=false;
        this.partieAjout=true;
         this.partieImport=false;
    }

    afficherPartieImport()
    {
        this.partieImport=true;
        this.partieAffiche=false;
        this.partieAjout=false;

    }




getVilleByGouvernorat()
{
    this.villeService.getVilleByGouvernorat(this.gForListVille.id_gouvernorat).subscribe(posts =>
              {
                this.villes = posts;
              });

}
 recupererIdContrat(a : number)
    {
        this.n=a;
    }



    
    editContrat(a)
    {
    this.recupererIdContrat(a);
    this.contratService.getContrat(a)
              .subscribe ( posts =>
              {
               
                this.addresse=posts.addresse;
                this.date_debut=posts.date_debut;
                this.date_fin=posts.date_fin;
                this.nom=posts.nom;
                this.num_contrat=posts.num_contrat;
                this.prenom=posts.prenom;
                
                
                
             });
    }

    validerEditContrat()
    {
    this.contratService.modifierContrat(this.n,this.addresse,this.date_debut,this.date_fin,this.nom
    ,this.num_contrat,this.prenom
    ,this.villeForEdit.id_ville,this.marqueForEdit.id_marque,this.packForEdit.id_pack)
        .subscribe ( posts =>
                {
                    })
                    window.location.reload();


    }
    ajouterContrat()
    {
        this.contratService.ajouterContrat(this.addresseForAjout 
        ,this.date_debutForAjout
        ,this.date_finForAjout
        ,this.nomForAjout
        ,this.num_contratForAjout
        ,this.prenomForAjout
        ,this.villeForAjout.id_ville
        ,this.marqueForAjout.id_marque
        ,this.packForAjout.id_pack
       )
        .subscribe(posts=>
                        { });

        }

        verifierDate()
        {
            if (this.date_finForAjout < this.date_debutForAjout)
            this.dateInvalid=true;
            else
              this.dateInvalid=false;

        }

importerContrat()
{
    this.contratService.importerContrat()
        .subscribe ( posts =>
                {
                    })
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

interface Marque {
    id_marque : number;
    nom_marque : string;
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