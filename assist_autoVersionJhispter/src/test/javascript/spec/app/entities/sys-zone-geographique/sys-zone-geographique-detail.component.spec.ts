/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SysZoneGeographiqueDetailComponent } from '../../../../../../main/webapp/app/entities/sys-zone-geographique/sys-zone-geographique-detail.component';
import { SysZoneGeographiqueService } from '../../../../../../main/webapp/app/entities/sys-zone-geographique/sys-zone-geographique.service';
import { SysZoneGeographique } from '../../../../../../main/webapp/app/entities/sys-zone-geographique/sys-zone-geographique.model';

describe('Component Tests', () => {

    describe('SysZoneGeographique Management Detail Component', () => {
        let comp: SysZoneGeographiqueDetailComponent;
        let fixture: ComponentFixture<SysZoneGeographiqueDetailComponent>;
        let service: SysZoneGeographiqueService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [SysZoneGeographiqueDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SysZoneGeographiqueService,
                    JhiEventManager
                ]
            }).overrideTemplate(SysZoneGeographiqueDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SysZoneGeographiqueDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysZoneGeographiqueService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SysZoneGeographique(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sysZoneGeographique).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
