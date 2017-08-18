import { BaseEntity } from './../../shared';

export class SysVille implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public gouvernorat?: BaseEntity,
    ) {
    }
}
