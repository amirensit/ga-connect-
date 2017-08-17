import { BaseEntity } from './../../shared';

export class RefTypeService implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public packs?: BaseEntity[],
    ) {
    }
}
