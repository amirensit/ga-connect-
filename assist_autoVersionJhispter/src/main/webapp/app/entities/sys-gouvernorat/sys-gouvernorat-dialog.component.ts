import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SysGouvernorat } from './sys-gouvernorat.model';
import { SysGouvernoratPopupService } from './sys-gouvernorat-popup.service';
import { SysGouvernoratService } from './sys-gouvernorat.service';
import { SysZoneGeographique, SysZoneGeographiqueService } from '../sys-zone-geographique';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-sys-gouvernorat-dialog',
    templateUrl: './sys-gouvernorat-dialog.component.html'
})
export class SysGouvernoratDialogComponent implements OnInit {

    sysGouvernorat: SysGouvernorat;
    isSaving: boolean;

    syszonegeographiques: SysZoneGeographique[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private sysGouvernoratService: SysGouvernoratService,
        private sysZoneGeographiqueService: SysZoneGeographiqueService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sysZoneGeographiqueService.query()
            .subscribe((res: ResponseWrapper) => { this.syszonegeographiques = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sysGouvernorat.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sysGouvernoratService.update(this.sysGouvernorat));
        } else {
            this.subscribeToSaveResponse(
                this.sysGouvernoratService.create(this.sysGouvernorat));
        }
    }

    private subscribeToSaveResponse(result: Observable<SysGouvernorat>) {
        result.subscribe((res: SysGouvernorat) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: SysGouvernorat) {
        this.eventManager.broadcast({ name: 'sysGouvernoratListModification', content: 'OK'});
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

    trackSysZoneGeographiqueById(index: number, item: SysZoneGeographique) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sys-gouvernorat-popup',
    template: ''
})
export class SysGouvernoratPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysGouvernoratPopupService: SysGouvernoratPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sysGouvernoratPopupService
                    .open(SysGouvernoratDialogComponent as Component, params['id']);
            } else {
                this.sysGouvernoratPopupService
                    .open(SysGouvernoratDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
