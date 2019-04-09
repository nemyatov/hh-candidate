import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { CandidateSharedModule } from 'app/shared';
import {
    CandidateComponent,
    CandidateDetailComponent,
    CandidateUpdateComponent,
    CandidateDeletePopupComponent,
    CandidateDeleteDialogComponent,
    candidateRoute,
    candidatePopupRoute
} from './';

const ENTITY_STATES = [...candidateRoute, ...candidatePopupRoute];

@NgModule({
    imports: [CandidateSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CandidateComponent,
        CandidateDetailComponent,
        CandidateUpdateComponent,
        CandidateDeleteDialogComponent,
        CandidateDeletePopupComponent
    ],
    entryComponents: [CandidateComponent, CandidateUpdateComponent, CandidateDeleteDialogComponent, CandidateDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CandidateCandidateModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
