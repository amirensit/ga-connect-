import { BaseEntity } from './../../shared';
import {RefTypeService} from '../ref-type-service'
export class RefPack implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public description?: string,
        public maxServices?: number,
        public maxKil?: number,
        public isBloque?: boolean,
        public contrats?: BaseEntity[],
        public typeServices?: RefTypeService[],
        public compagnies?: BaseEntity[],
    ) {
        this.isBloque = false;
    }
}
