import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    RefMarqueService,
    RefMarquePopupService,
    RefMarqueComponent,
    RefMarqueDetailComponent,
    RefMarqueDialogComponent,
    RefMarquePopupComponent,
    RefMarqueDeletePopupComponent,
    RefMarqueDeleteDialogComponent,
    refMarqueRoute,
    refMarquePopupRoute,
} from './';

const ENTITY_STATES = [
    ...refMarqueRoute,
    ...refMarquePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RefMarqueComponent,
        RefMarqueDetailComponent,
        RefMarqueDialogComponent,
        RefMarqueDeleteDialogComponent,
        RefMarquePopupComponent,
        RefMarqueDeletePopupComponent,
    ],
    entryComponents: [
        RefMarqueComponent,
        RefMarqueDialogComponent,
        RefMarquePopupComponent,
        RefMarqueDeleteDialogComponent,
        RefMarqueDeletePopupComponent,
    ],
    providers: [
        RefMarqueService,
        RefMarquePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceRefMarqueModule {}
