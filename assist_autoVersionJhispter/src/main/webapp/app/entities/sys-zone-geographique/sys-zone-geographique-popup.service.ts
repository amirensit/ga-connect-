import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SysZoneGeographique } from './sys-zone-geographique.model';
import { SysZoneGeographiqueService } from './sys-zone-geographique.service';

@Injectable()
export class SysZoneGeographiquePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sysZoneGeographiqueService: SysZoneGeographiqueService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.sysZoneGeographiqueService.find(id).subscribe((sysZoneGeographique) => {
                    this.ngbModalRef = this.sysZoneGeographiqueModalRef(component, sysZoneGeographique);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sysZoneGeographiqueModalRef(component, new SysZoneGeographique());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sysZoneGeographiqueModalRef(component: Component, sysZoneGeographique: SysZoneGeographique): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sysZoneGeographique = sysZoneGeographique;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
