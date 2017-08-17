import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    RefTypeServiceService,
    RefTypeServicePopupService,
    RefTypeServiceComponent,
    RefTypeServiceDetailComponent,
    RefTypeServiceDialogComponent,
    RefTypeServicePopupComponent,
    RefTypeServiceDeletePopupComponent,
    RefTypeServiceDeleteDialogComponent,
    refTypeServiceRoute,
    refTypeServicePopupRoute,
} from './';

const ENTITY_STATES = [
    ...refTypeServiceRoute,
    ...refTypeServicePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RefTypeServiceComponent,
        RefTypeServiceDetailComponent,
        RefTypeServiceDialogComponent,
        RefTypeServiceDeleteDialogComponent,
        RefTypeServicePopupComponent,
        RefTypeServiceDeletePopupComponent,
    ],
    entryComponents: [
        RefTypeServiceComponent,
        RefTypeServiceDialogComponent,
        RefTypeServicePopupComponent,
        RefTypeServiceDeleteDialogComponent,
        RefTypeServiceDeletePopupComponent,
    ],
    providers: [
        RefTypeServiceService,
        RefTypeServicePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceRefTypeServiceModule {}
