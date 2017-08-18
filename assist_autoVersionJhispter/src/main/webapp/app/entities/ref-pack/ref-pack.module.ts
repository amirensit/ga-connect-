import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    RefPackService,
    RefPackPopupService,
    RefPackComponent,
    RefPackDetailComponent,
    RefPackDialogComponent,
    RefPackPopupComponent,
    RefPackDeletePopupComponent,
    RefPackDeleteDialogComponent,
    refPackRoute,
    refPackPopupRoute,
} from './';

const ENTITY_STATES = [
    ...refPackRoute,
    ...refPackPopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RefPackComponent,
        RefPackDetailComponent,
        RefPackDialogComponent,
        RefPackDeleteDialogComponent,
        RefPackPopupComponent,
        RefPackDeletePopupComponent,
    ],
    entryComponents: [
        RefPackComponent,
        RefPackDialogComponent,
        RefPackPopupComponent,
        RefPackDeleteDialogComponent,
        RefPackDeletePopupComponent,
    ],
    providers: [
        RefPackService,
        RefPackPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceRefPackModule {}
