import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    RefCompagnieService,
    RefCompagniePopupService,
    RefCompagnieComponent,
    RefCompagnieDetailComponent,
    RefCompagnieDialogComponent,
    RefCompagniePopupComponent,
    RefCompagnieDeletePopupComponent,
    RefCompagnieDeleteDialogComponent,
    refCompagnieRoute,
    refCompagniePopupRoute,
} from './';

const ENTITY_STATES = [
    ...refCompagnieRoute,
    ...refCompagniePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RefCompagnieComponent,
        RefCompagnieDetailComponent,
        RefCompagnieDialogComponent,
        RefCompagnieDeleteDialogComponent,
        RefCompagniePopupComponent,
        RefCompagnieDeletePopupComponent,
    ],
    entryComponents: [
        RefCompagnieComponent,
        RefCompagnieDialogComponent,
        RefCompagniePopupComponent,
        RefCompagnieDeleteDialogComponent,
        RefCompagnieDeletePopupComponent,
    ],
    providers: [
        RefCompagnieService,
        RefCompagniePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceRefCompagnieModule {}
