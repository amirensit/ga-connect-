import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Voiture } from './voiture.model';
import { VoitureService } from './voiture.service';

@Injectable()
export class VoiturePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private voitureService: VoitureService

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
                this.voitureService.find(id).subscribe((voiture) => {
                    if (voiture.miseCirculation) {
                        voiture.miseCirculation = {
                            year: voiture.miseCirculation.getFullYear(),
                            month: voiture.miseCirculation.getMonth() + 1,
                            day: voiture.miseCirculation.getDate()
                        };
                    }
                    this.ngbModalRef = this.voitureModalRef(component, voiture);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.voitureModalRef(component, new Voiture());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    voitureModalRef(component: Component, voiture: Voiture): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.voiture = voiture;
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
