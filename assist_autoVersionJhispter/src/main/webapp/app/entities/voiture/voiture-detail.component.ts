import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Voiture } from './voiture.model';
import { VoitureService } from './voiture.service';

@Component({
    selector: 'jhi-voiture-detail',
    templateUrl: './voiture-detail.component.html'
})
export class VoitureDetailComponent implements OnInit, OnDestroy {

    voiture: Voiture;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private voitureService: VoitureService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVoitures();
    }

    load(id) {
        this.voitureService.find(id).subscribe((voiture) => {
            this.voiture = voiture;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVoitures() {
        this.eventSubscriber = this.eventManager.subscribe(
            'voitureListModification',
            (response) => this.load(this.voiture.id)
        );
    }
}
