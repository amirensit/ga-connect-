/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { VoitureDetailComponent } from '../../../../../../main/webapp/app/entities/voiture/voiture-detail.component';
import { VoitureService } from '../../../../../../main/webapp/app/entities/voiture/voiture.service';
import { Voiture } from '../../../../../../main/webapp/app/entities/voiture/voiture.model';

describe('Component Tests', () => {

    describe('Voiture Management Detail Component', () => {
        let comp: VoitureDetailComponent;
        let fixture: ComponentFixture<VoitureDetailComponent>;
        let service: VoitureService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [VoitureDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    VoitureService,
                    JhiEventManager
                ]
            }).overrideTemplate(VoitureDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VoitureDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoitureService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Voiture(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.voiture).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
