import { BaseEntity } from './../../shared';

export class SysZoneGeographique implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public gouvernorats?: BaseEntity[],
    ) {
    }
}
