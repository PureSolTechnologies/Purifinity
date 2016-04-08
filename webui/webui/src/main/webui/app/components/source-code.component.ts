import {Component, Input} from 'angular2/core';

@Component({
    selector: 'source-code',
    template:
`<div class="sourceCode">
  <table *ngIf="sourceCode" class="sourceCode">
    <tbody class="sourceCode">
      <tr *ngFor="#line of sourceCode.lines">
    <td class="lineNumber">
      {{line.lineNumber}}&nbsp;
    </td>
    <td>
      <pre class="sourceLine">{{line.line}}</pre>
    </td>
      </tr>
    </tbody>
  </table>
</div>
`
})
export class SourceCodeComponent {

    @Input() 
    private sourceCode: any;

}