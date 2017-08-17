import { Component , Input } from '@angular/core';

import { Router } from "@angular/router";
import {GouvernoratService} from "../../services/gouvernorat.service"



@Component({
    selector: 'icons-cmp',
    moduleId: module.id,
    templateUrl: 'gouvernorat.component.html',
    providers: [GouvernoratService],
})

export class GouvernoratComponent{
    nom_gouvernorat : string;

    constructor( private gouvernoratService  : GouvernoratService,private route :Router){ }

    ajouterGouvernorat()
    {
        this.gouvernoratService.ajouterGouvernorat(this.nom_gouvernorat )
            .subscribe( posts =>
              {
                 })
    }
}