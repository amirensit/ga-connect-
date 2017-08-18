import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    RefRemorqueurService,
    RefRemorqueurPopupService,
    RefRemorqueurComponent,
    RefRemorqueurDetailComponent,
    RefRemorqueurDialogComponent,
    RefRemorqueurPopupComponent,
    RefRemorqueurDeletePopupComponent,
    RefRemorqueurDeleteDialogComponent,
    refRemorqueurRoute,
    refRemorqueurPopupRoute,
} from './';

const ENTITY_STATES = [
    ...refRemorqueurRoute,
    ...refRemorqueurPopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RefRemorqueurComponent,
        RefRemorqueurDetailComponent,
        RefRemorqueurDialogComponent,
        RefRemorqueurDeleteDialogComponent,
        RefRemorqueurPopupComponent,
        RefRemorqueurDeletePopupComponent,
    ],
    entryComponents: [
        RefRemorqueurComponent,
        RefRemorqueurDialogComponent,
        RefRemorqueurPopupComponent,
        RefRemorqueurDeleteDialogComponent,
        RefRemorqueurDeletePopupComponent,
    ],
    providers: [
        RefRemorqueurService,
        RefRemorqueurPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceRefRemorqueurModule {}
