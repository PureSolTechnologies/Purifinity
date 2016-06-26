import {HashId} from '../HashId';
import {Version} from '../Version';
import {Classification} from '../Classification';
import {Severity} from '../Severity';
import {CodeRangeType} from '../CodeRangeType';

export class SingleIssue {

    constructor(public projectId: string,
        public runId: string,
        public evaluatorId: string,
        public evaluatorVersion: Version,
        public codeRangeType: CodeRangeType,
        public hashId: HashId,
        public codeRangeName: string,
        public time: string,
        public sourceCodeLocation: any,
        public languageName: string,
        public languageVersion: string,
        public severity: Severity,
        public classification: Classification,
        public startLine: number,
        public startColumn: number,
        public lineCount: number,
        public length: number,
        public weight: number,
        public parameter: any) {
    }

}
