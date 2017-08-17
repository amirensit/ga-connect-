import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    PersonneService,
    PersonnePopupService,
    PersonneComponent,
    PersonneDetailComponent,
    PersonneDialogComponent,
    PersonnePopupComponent,
    PersonneDeletePopupComponent,
    PersonneDeleteDialogComponent,
    personneRoute,
    personnePopupRoute,
} from './';

const ENTITY_STATES = [
    ...personneRoute,
    ...personnePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PersonneComponent,
        PersonneDetailComponent,
        PersonneDialogComponent,
        PersonneDeleteDialogComponent,
        PersonnePopupComponent,
        PersonneDeletePopupComponent,
    ],
    entryComponents: [
        PersonneComponent,
        PersonneDialogComponent,
        PersonnePopupComponent,
        PersonneDeleteDialogComponent,
        PersonneDeletePopupComponent,
    ],
    providers: [
        PersonneService,
        PersonnePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistancePersonneModule {}
