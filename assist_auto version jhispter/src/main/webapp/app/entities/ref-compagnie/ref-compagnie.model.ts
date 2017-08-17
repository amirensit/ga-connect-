import { BaseEntity } from './../../shared';

export class RefCompagnie implements BaseEntity {
    constructor(
        public id?: number,
        public numero?: number,
        public nom?: string,
        public isBloque?: boolean,
        public adresse?: string,
        public latitude?: string,
        public longitude?: string,
        public ville?: BaseEntity,
        public contrats?: BaseEntity[],
        public packs?: BaseEntity[],
    ) {
        this.isBloque = false;
    }
}
