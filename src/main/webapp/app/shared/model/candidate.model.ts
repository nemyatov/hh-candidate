export interface ICandidate {
    id?: number;
    url?: string;
    email?: string;
    name?: string;
}

export class Candidate implements ICandidate {
    constructor(public id?: number, public url?: string, public email?: string, public name?: string) {}
}
