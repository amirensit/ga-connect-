import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RefPack } from './ref-pack.model';
import { RefPackPopupService } from './ref-pack-popup.service';
import { RefPackService } from './ref-pack.service';
import { RefTypeService, RefTypeServiceService } from '../ref-type-service';
import { RefCompagnie, RefCompagnieService } from '../ref-compagnie';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ref-pack-dialog',
    templateUrl: './ref-pack-dialog.component.html'
})
export class RefPackDialogComponent implements OnInit {

    refPack: RefPack;
    isSaving: boolean;

    reftypeservices: RefTypeService[];
    refType : RefTypeService;
    refcompagnies: RefCompagnie[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private refPackService: RefPackService,
        private refTypeServiceService: RefTypeServiceService,
        private refCompagnieService: RefCompagnieService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        if (this.refPack.id === undefined) {
            this.refPack.typeServices = [];
        } 

        
        this.refType=new RefTypeService();
        this.isSaving = false;
        this.refTypeServiceService.query()
            .subscribe((res: ResponseWrapper) => { this.reftypeservices = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.refCompagnieService.query()
            .subscribe((res: ResponseWrapper) => { this.refcompagnies = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.refPack.typeServices.push(this.refType);
        this.isSaving = true;
        if (this.refPack.id !== undefined) {
            this.subscribeToSaveResponse(
                this.refPackService.update(this.refPack));
        } else {
            this.subscribeToSaveResponse(
                this.refPackService.create(this.refPack));
        }
    }

    private subscribeToSaveResponse(result: Observable<RefPack>) {
        result.subscribe((res: RefPack) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RefPack) {
        this.eventManager.broadcast({ name: 'refPackListModification', content: 'OK'});
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

    trackRefTypeServiceById(index: number, item: RefTypeService) {
        return item.id;
    }

    trackRefCompagnieById(index: number, item: RefCompagnie) {
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
    selector: 'jhi-ref-pack-popup',
    template: ''
})
export class RefPackPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refPackPopupService: RefPackPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.refPackPopupService
                    .open(RefPackDialogComponent as Component, params['id']);
            } else {
                this.refPackPopupService
                    .open(RefPackDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
