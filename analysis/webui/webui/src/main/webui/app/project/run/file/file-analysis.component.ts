import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {FileStore} from '../../../commons/purifinity/FileStore';
import {VersionPipe} from '../../../commons/pipes/version.pipe';
import {FileMenuComponent} from './file-menu.component';

@Component({
    selector: 'file-analysis',
    directives: [
        FileMenuComponent
    ],
    pipes: [
        VersionPipe
    ],
    templateUrl: '../../../../html/project/run/file/analysis.html'
})
export class FileAnalysisComponent {

    private projectId: string;
    private runId: string;
    private hashId: string;

    private analyzerSelection = undefined;
    private analyses = [];
    private usts = {};
    private selectedUST = undefined;

    constructor(private routeParams: RouteParams, private fileStore: FileStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
        this.hashId = routeParams.get('hashId');

        let component = this;
        let versionPipe = new VersionPipe();
        fileStore
            .wasAnalyzed(
            this.hashId,
            function(data, status) {
                if (data) {
                    fileStore
                        .loadAnalyses(
                        component.hashId,
                        function(data: any[]) {
                            component.analyses = data;
                            component.analyses
                                .forEach(function(
                                    analysis) {
                                    var id = analysis.analysisInformation.analyzerId
                                        + versionPipe.transform(analysis.analysisInformation.analyzerVersion, []);
                                    analysis.analyzableCodeRanges
                                        .forEach(function(
                                            codeRange) {
                                            if (codeRange.type === "FILE") {
                                                component.usts[id] = codeRange.ust;
                                            }
                                        });

                                });
                        }, function(response: Response) {
                        });
                }
            }, function(response: Response) {
            });
    }

}
