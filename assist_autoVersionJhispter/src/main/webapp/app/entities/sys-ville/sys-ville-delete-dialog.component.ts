import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SysVille } from './sys-ville.model';
import { SysVillePopupService } from './sys-ville-popup.service';
import { SysVilleService } from './sys-ville.service';

@Component({
    selector: 'jhi-sys-ville-delete-dialog',
    templateUrl: './sys-ville-delete-dialog.component.html'
})
export class SysVilleDeleteDialogComponent {

    sysVille: SysVille;

    constructor(
        private sysVilleService: SysVilleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysVilleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sysVilleListModification',
                content: 'Deleted an sysVille'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-ville-delete-popup',
    template: ''
})
export class SysVilleDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysVillePopupService: SysVillePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sysVillePopupService
                .open(SysVilleDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
