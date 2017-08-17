import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SysGouvernorat } from './sys-gouvernorat.model';
import { SysGouvernoratPopupService } from './sys-gouvernorat-popup.service';
import { SysGouvernoratService } from './sys-gouvernorat.service';

@Component({
    selector: 'jhi-sys-gouvernorat-delete-dialog',
    templateUrl: './sys-gouvernorat-delete-dialog.component.html'
})
export class SysGouvernoratDeleteDialogComponent {

    sysGouvernorat: SysGouvernorat;

    constructor(
        private sysGouvernoratService: SysGouvernoratService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysGouvernoratService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sysGouvernoratListModification',
                content: 'Deleted an sysGouvernorat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-gouvernorat-delete-popup',
    template: ''
})
export class SysGouvernoratDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysGouvernoratPopupService: SysGouvernoratPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sysGouvernoratPopupService
                .open(SysGouvernoratDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
