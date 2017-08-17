import { BaseEntity } from './../../shared';
import {Assuree} from '../assuree';
import {RefTypeService} from '../ref-type-service'; 
export class DossierRmq implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: string,
        public distance?: number,
        public observation?: string,
        public assuree?: Assuree,
        public typeService?: RefTypeService,
        public lieu?: BaseEntity,
        public destination?: BaseEntity,
        public remorqueur?: BaseEntity,
    ) {
    }
}
