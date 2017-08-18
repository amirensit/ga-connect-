import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { SysZoneGeographique } from './sys-zone-geographique.model';
import { SysZoneGeographiqueService } from './sys-zone-geographique.service';

@Component({
    selector: 'jhi-sys-zone-geographique-detail',
    templateUrl: './sys-zone-geographique-detail.component.html'
})
export class SysZoneGeographiqueDetailComponent implements OnInit, OnDestroy {

    sysZoneGeographique: SysZoneGeographique;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sysZoneGeographiqueService: SysZoneGeographiqueService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSysZoneGeographiques();
    }

    load(id) {
        this.sysZoneGeographiqueService.find(id).subscribe((sysZoneGeographique) => {
            this.sysZoneGeographique = sysZoneGeographique;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSysZoneGeographiques() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sysZoneGeographiqueListModification',
            (response) => this.load(this.sysZoneGeographique.id)
        );
    }
}
