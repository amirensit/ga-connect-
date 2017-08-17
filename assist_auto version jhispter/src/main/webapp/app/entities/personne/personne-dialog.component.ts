import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Personne } from './personne.model';
import { PersonnePopupService } from './personne-popup.service';
import { PersonneService } from './personne.service';
import { SysVille, SysVilleService } from '../sys-ville';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-personne-dialog',
    templateUrl: './personne-dialog.component.html'
})
export class PersonneDialogComponent implements OnInit {

    personne: Personne;
    isSaving: boolean;

    villes: SysVille[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private personneService: PersonneService,
        private sysVilleService: SysVilleService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sysVilleService
            .query({filter: 'personne-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.personne.ville || !this.personne.ville.id) {
                    this.villes = res.json;
                } else {
                    this.sysVilleService
                        .find(this.personne.ville.id)
                        .subscribe((subRes: SysVille) => {
                            this.villes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.personne.id !== undefined) {
            this.subscribeToSaveResponse(
                this.personneService.update(this.personne));
        } else {
            this.subscribeToSaveResponse(
                this.personneService.create(this.personne));
        }
    }

    private subscribeToSaveResponse(result: Observable<Personne>) {
        result.subscribe((res: Personne) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Personne) {
        this.eventManager.broadcast({ name: 'personneListModification', content: 'OK'});
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

    trackSysVilleById(index: number, item: SysVille) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-personne-popup',
    template: ''
})
export class PersonnePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private personnePopupService: PersonnePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.personnePopupService
                    .open(PersonneDialogComponent as Component, params['id']);
            } else {
                this.personnePopupService
                    .open(PersonneDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
