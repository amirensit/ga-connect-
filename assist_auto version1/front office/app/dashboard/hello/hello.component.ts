import { Component , Input } from '@angular/core';

import { Router } from "@angular/router";




@Component({
    
    moduleId: module.id,
    templateUrl: 'hello.component.html',
    providers: [],
})

export class HelloComponent{
    

    constructor( private route :Router){ }

    
}