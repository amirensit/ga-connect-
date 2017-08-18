/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DossierRmqDetailComponent } from '../../../../../../main/webapp/app/entities/dossier-rmq/dossier-rmq-detail.component';
import { DossierRmqService } from '../../../../../../main/webapp/app/entities/dossier-rmq/dossier-rmq.service';
import { DossierRmq } from '../../../../../../main/webapp/app/entities/dossier-rmq/dossier-rmq.model';

describe('Component Tests', () => {

    describe('DossierRmq Management Detail Component', () => {
        let comp: DossierRmqDetailComponent;
        let fixture: ComponentFixture<DossierRmqDetailComponent>;
        let service: DossierRmqService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [DossierRmqDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DossierRmqService,
                    JhiEventManager
                ]
            }).overrideTemplate(DossierRmqDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DossierRmqDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DossierRmqService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new DossierRmq(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.dossierRmq).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
