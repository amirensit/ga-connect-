import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AssistanceVoitureModule} from '../voiture/voiture.module'
import { AssistanceSharedModule } from '../../shared';
import {
    ContratService,
    ContratPopupService,
    ContratComponent,
    ContratDetailComponent,
    ContratDialogComponent,
    ContratPopupComponent,
    ContratDeletePopupComponent,
    ContratDeleteDialogComponent,
    contratRoute,
    
    contratPopupRoute,
} from './';

const ENTITY_STATES = [
    ...contratRoute,
    ...contratPopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        AssistanceVoitureModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ContratComponent,
        ContratDetailComponent,
        ContratDialogComponent,
        ContratDeleteDialogComponent,
        ContratPopupComponent,
        ContratDeletePopupComponent,
    ],
    entryComponents: [
        ContratComponent, 
        ContratDialogComponent,
        ContratPopupComponent,
        ContratDeleteDialogComponent,
        ContratDeletePopupComponent,
        
    ],
    providers: [
        ContratService,
        ContratPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceContratModule {}
