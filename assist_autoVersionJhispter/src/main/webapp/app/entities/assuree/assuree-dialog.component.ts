import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Assuree } from './assuree.model';
import { AssureePopupService } from './assuree-popup.service';
import { AssureeService } from './assuree.service';
import { Personne, PersonneService } from '../personne';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-assuree-dialog',
    templateUrl: './assuree-dialog.component.html'
})
export class AssureeDialogComponent implements OnInit {

    assuree: Assuree;
    isSaving: boolean;

    personnes: Personne[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private assureeService: AssureeService,
        private personneService: PersonneService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.personneService
            .query({filter: 'assuree-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.assuree.personne || !this.assuree.personne.id) {
                    this.personnes = res.json;
                } else {
                    this.personneService
                        .find(this.assuree.personne.id)
                        .subscribe((subRes: Personne) => {
                            this.personnes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.assuree.id !== undefined) {
            this.subscribeToSaveResponse(
                this.assureeService.update(this.assuree));
        } else {
            this.subscribeToSaveResponse(
                this.assureeService.create(this.assuree));
        }
    }
    
    private subscribeToSaveResponse(result: Observable<Assuree>) {
        result.subscribe((res: Assuree) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Assuree) {
        this.eventManager.broadcast({ name: 'assureeListModification', content: 'OK'});
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

    trackPersonneById(index: number, item: Personne) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-assuree-popup',
    template: ''
})
export class AssureePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assureePopupService: AssureePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.assureePopupService
                    .open(AssureeDialogComponent as Component, params['id']);
            } else {
                this.assureePopupService
                    .open(AssureeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
