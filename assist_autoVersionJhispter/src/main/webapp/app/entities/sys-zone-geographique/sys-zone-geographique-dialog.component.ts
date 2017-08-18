import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SysZoneGeographique } from './sys-zone-geographique.model';
import { SysZoneGeographiquePopupService } from './sys-zone-geographique-popup.service';
import { SysZoneGeographiqueService } from './sys-zone-geographique.service';

@Component({
    selector: 'jhi-sys-zone-geographique-dialog',
    templateUrl: './sys-zone-geographique-dialog.component.html'
})
export class SysZoneGeographiqueDialogComponent implements OnInit {

    sysZoneGeographique: SysZoneGeographique;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private sysZoneGeographiqueService: SysZoneGeographiqueService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sysZoneGeographique.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sysZoneGeographiqueService.update(this.sysZoneGeographique));
        } else {
            this.subscribeToSaveResponse(
                this.sysZoneGeographiqueService.create(this.sysZoneGeographique));
        }
    }

    private subscribeToSaveResponse(result: Observable<SysZoneGeographique>) {
        result.subscribe((res: SysZoneGeographique) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: SysZoneGeographique) {
        this.eventManager.broadcast({ name: 'sysZoneGeographiqueListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-sys-zone-geographique-popup',
    template: ''
})
export class SysZoneGeographiquePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysZoneGeographiquePopupService: SysZoneGeographiquePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sysZoneGeographiquePopupService
                    .open(SysZoneGeographiqueDialogComponent as Component, params['id']);
            } else {
                this.sysZoneGeographiquePopupService
                    .open(SysZoneGeographiqueDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
