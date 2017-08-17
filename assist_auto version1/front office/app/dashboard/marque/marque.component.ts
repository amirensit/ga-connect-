import { Component , Input } from '@angular/core';
import {MarqueService} from '../../services/marque.service';
import { Router } from "@angular/router";
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'marque.component.html',
    providers: [MarqueService],
})

export class MarqueComponent{


n : any;
nom_marque : string;
partieAffiche : boolean;
partieAjout : boolean;
nom_marqueForAjout : string;
marques : Marque[];

pageMarques : any;
motCle:string="";
pageCourante:number=0;
size:number=4;
pages:any[];




constructor(private _flashMessagesService: FlashMessagesService,private  marqueService: MarqueService,private route :Router){ }

ngOnInit(){
     
     this.marqueService.getAllMarque()
         .subscribe ( posts =>
              {
                this.marques = posts;
                
              }
            );

    this.partieAffiche=true;
    this.partieAjout=false;
    

    
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

    recupererIdMarque(a : number)
    {
        this.n=a;
    }

    editMarque(a)
    {
        this.recupererIdMarque(a);
        this.marqueService.getMarque(a)
              .subscribe ( posts =>
              {
                this.nom_marque=posts.nom_marque;
             });
    }

    validerSupprimerMarque(a : number)
    {
        this.marqueService.supprimerMarque(a).subscribe(posts=>
                  { })
       
        window.location.reload();

    }

    

    validerEditMarque()
    {
        this.marqueService.modifierMarque(this.n,this.nom_marque )
            .subscribe ( posts =>
              {
                 })
    }

    ajouterMarque()
    {
        this.marqueService.ajouterMarque(this.nom_marqueForAjout )
            .subscribe( posts =>
              {
                 })
    }

    findMarqueForFiltre()
    {
        
        this.marqueService.aaaa(this.motCle,this.pageCourante,this.size)
        .subscribe( response => 
        {
            this.pageMarques= response.content;
            this.pages = new Array (response.totalPages);
            console.log(this.pageMarques.content);
        })
    }

    getMarques()
    {
        
        this.pageCourante=0;
        this.findMarqueForFiltre();
    }

    goToPage(p)
    {
    this.pageCourante=p;
    this.findMarqueForFiltre();
    }




}

interface Marque {
    id_marque : number;
    nom_marque : string;
}