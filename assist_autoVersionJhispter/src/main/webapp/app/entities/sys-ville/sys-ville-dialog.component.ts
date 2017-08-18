import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SysVille } from './sys-ville.model';
import { SysVillePopupService } from './sys-ville-popup.service';
import { SysVilleService } from './sys-ville.service';
import { SysGouvernorat, SysGouvernoratService } from '../sys-gouvernorat';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-sys-ville-dialog',
    templateUrl: './sys-ville-dialog.component.html'
})
export class SysVilleDialogComponent implements OnInit {

    sysVille: SysVille;
    isSaving: boolean;

    sysgouvernorats: SysGouvernorat[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private sysVilleService: SysVilleService,
        private sysGouvernoratService: SysGouvernoratService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sysGouvernoratService.query()
            .subscribe((res: ResponseWrapper) => { this.sysgouvernorats = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sysVille.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sysVilleService.update(this.sysVille));
        } else {
            this.subscribeToSaveResponse(
                this.sysVilleService.create(this.sysVille));
        }
    }

    private subscribeToSaveResponse(result: Observable<SysVille>) {
        result.subscribe((res: SysVille) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: SysVille) {
        this.eventManager.broadcast({ name: 'sysVilleListModification', content: 'OK'});
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

    trackSysGouvernoratById(index: number, item: SysGouvernorat) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sys-ville-popup',
    template: ''
})
export class SysVillePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysVillePopupService: SysVillePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sysVillePopupService
                    .open(SysVilleDialogComponent as Component, params['id']);
            } else {
                this.sysVillePopupService
                    .open(SysVilleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
