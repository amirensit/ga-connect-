import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RefPack } from './ref-pack.model';
import { RefPackPopupService } from './ref-pack-popup.service';
import { RefPackService } from './ref-pack.service';

@Component({
    selector: 'jhi-ref-pack-delete-dialog',
    templateUrl: './ref-pack-delete-dialog.component.html'
})
export class RefPackDeleteDialogComponent {

    refPack: RefPack;

    constructor(
        private refPackService: RefPackService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refPackService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'refPackListModification',
                content: 'Deleted an refPack'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-pack-delete-popup',
    template: ''
})
export class RefPackDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refPackPopupService: RefPackPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.refPackPopupService
                .open(RefPackDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
