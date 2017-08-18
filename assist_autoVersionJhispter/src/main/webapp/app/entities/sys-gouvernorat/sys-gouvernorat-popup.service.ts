import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SysGouvernorat } from './sys-gouvernorat.model';
import { SysGouvernoratService } from './sys-gouvernorat.service';

@Injectable()
export class SysGouvernoratPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sysGouvernoratService: SysGouvernoratService

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
                this.sysGouvernoratService.find(id).subscribe((sysGouvernorat) => {
                    this.ngbModalRef = this.sysGouvernoratModalRef(component, sysGouvernorat);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.sysGouvernoratModalRef(component, new SysGouvernorat());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    sysGouvernoratModalRef(component: Component, sysGouvernorat: SysGouvernorat): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sysGouvernorat = sysGouvernorat;
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
