import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    DossierRmqService,
    DossierRmqPopupService,
    DossierRmqComponent,
    DossierRmqDetailComponent,
    DossierRmqDialogComponent,
    DossierRmqPopupComponent,
    DossierRmqDeletePopupComponent,
    DossierRmqDeleteDialogComponent,
    dossierRmqRoute,
    dossierRmqPopupRoute,
} from './';

const ENTITY_STATES = [
    ...dossierRmqRoute,
    ...dossierRmqPopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DossierRmqComponent,
        DossierRmqDetailComponent,
        DossierRmqDialogComponent,
        DossierRmqDeleteDialogComponent,
        DossierRmqPopupComponent,
        DossierRmqDeletePopupComponent,
    ],
    entryComponents: [
        DossierRmqComponent,
        DossierRmqDialogComponent,
        DossierRmqPopupComponent,
        DossierRmqDeleteDialogComponent,
        DossierRmqDeletePopupComponent,
    ],
    providers: [
        DossierRmqService,
        DossierRmqPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceDossierRmqModule {}
