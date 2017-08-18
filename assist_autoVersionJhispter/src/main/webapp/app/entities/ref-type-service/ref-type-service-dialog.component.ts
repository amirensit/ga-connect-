import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RefTypeService } from './ref-type-service.model';
import { RefTypeServicePopupService } from './ref-type-service-popup.service';
import { RefTypeServiceService } from './ref-type-service.service';
import { RefPack, RefPackService } from '../ref-pack';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ref-type-service-dialog',
    templateUrl: './ref-type-service-dialog.component.html'
})
export class RefTypeServiceDialogComponent implements OnInit {

    refTypeService: RefTypeService;
    isSaving: boolean;

    refpacks: RefPack[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private refTypeServiceService: RefTypeServiceService,
        private refPackService: RefPackService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.refPackService.query()
            .subscribe((res: ResponseWrapper) => { this.refpacks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.refTypeService.id !== undefined) {
            this.subscribeToSaveResponse(
                this.refTypeServiceService.update(this.refTypeService));
        } else {
            this.subscribeToSaveResponse(
                this.refTypeServiceService.create(this.refTypeService));
        }
    }

    private subscribeToSaveResponse(result: Observable<RefTypeService>) {
        result.subscribe((res: RefTypeService) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RefTypeService) {
        this.eventManager.broadcast({ name: 'refTypeServiceListModification', content: 'OK'});
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

    trackRefPackById(index: number, item: RefPack) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-ref-type-service-popup',
    template: ''
})
export class RefTypeServicePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refTypeServicePopupService: RefTypeServicePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.refTypeServicePopupService
                    .open(RefTypeServiceDialogComponent as Component, params['id']);
            } else {
                this.refTypeServicePopupService
                    .open(RefTypeServiceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
