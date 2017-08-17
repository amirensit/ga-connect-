import { BaseEntity } from './../../shared';

export class SysGouvernorat implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public villes?: BaseEntity[],
        public zoneGeographique?: BaseEntity,
    ) {
    }
}
