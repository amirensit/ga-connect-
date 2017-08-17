import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SysZoneGeographique } from './sys-zone-geographique.model';
import { SysZoneGeographiquePopupService } from './sys-zone-geographique-popup.service';
import { SysZoneGeographiqueService } from './sys-zone-geographique.service';

@Component({
    selector: 'jhi-sys-zone-geographique-delete-dialog',
    templateUrl: './sys-zone-geographique-delete-dialog.component.html'
})
export class SysZoneGeographiqueDeleteDialogComponent {

    sysZoneGeographique: SysZoneGeographique;

    constructor(
        private sysZoneGeographiqueService: SysZoneGeographiqueService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysZoneGeographiqueService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sysZoneGeographiqueListModification',
                content: 'Deleted an sysZoneGeographique'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-zone-geographique-delete-popup',
    template: ''
})
export class SysZoneGeographiqueDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sysZoneGeographiquePopupService: SysZoneGeographiquePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sysZoneGeographiquePopupService
                .open(SysZoneGeographiqueDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
