import { BaseEntity } from './../../shared';

export class RefRemorqueur implements BaseEntity {
    constructor(
        public id?: number,
        public raisonSociale?: string,
        public numEtablissement?: string,
        public codeCategorie?: string,
        public codeTVA?: string,
        public matriculeFiscal?: string,
        public adresse?: string,
        public latitude?: string,
        public longitude?: string,
        public contact?: BaseEntity,
    ) {
    }
}
