import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Assuree } from './assuree.model';
import { AssureePopupService } from './assuree-popup.service';
import { AssureeService } from './assuree.service';

@Component({
    selector: 'jhi-assuree-delete-dialog',
    templateUrl: './assuree-delete-dialog.component.html'
})
export class AssureeDeleteDialogComponent {

    assuree: Assuree;

    constructor(
        private assureeService: AssureeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assureeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'assureeListModification',
                content: 'Deleted an assuree'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-assuree-delete-popup',
    template: ''
})
export class AssureeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assureePopupService: AssureePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.assureePopupService
                .open(AssureeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
