import { Component , Input } from '@angular/core';

import { Router } from "@angular/router";
import {VilleService} from "../../services/ville.service"



@Component({
    selector: 'notifications-cmp',
    moduleId: module.id,
    templateUrl: 'ville.component.html',
    providers: [VilleService],
})

export class VilleComponent{
    nom_ville : string;
    id_gouvernorat : number;

    constructor( private villeService  : VilleService,private route :Router){ }

    ajouterVille()
    {
        this.villeService.ajouterVille(this.nom_ville,this.id_gouvernorat )
            .subscribe( posts =>
              {
                 })
        
    }
}