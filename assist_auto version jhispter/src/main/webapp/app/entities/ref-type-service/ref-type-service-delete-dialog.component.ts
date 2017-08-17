import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RefTypeService } from './ref-type-service.model';
import { RefTypeServicePopupService } from './ref-type-service-popup.service';
import { RefTypeServiceService } from './ref-type-service.service';

@Component({
    selector: 'jhi-ref-type-service-delete-dialog',
    templateUrl: './ref-type-service-delete-dialog.component.html'
})
export class RefTypeServiceDeleteDialogComponent {

    refTypeService: RefTypeService;

    constructor(
        private refTypeServiceService: RefTypeServiceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refTypeServiceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'refTypeServiceListModification',
                content: 'Deleted an refTypeService'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-type-service-delete-popup',
    template: ''
})
export class RefTypeServiceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refTypeServicePopupService: RefTypeServicePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.refTypeServicePopupService
                .open(RefTypeServiceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
