import {Component} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {
    ROUTER_DIRECTIVES,
    RouteConfig
} from 'angular2/router';

import {HeaderComponent} from './header.component';
import {FooterComponent} from './footer.component';
// Main Application
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {AccountComponent} from './account.component';
import {ChangePasswordComponent} from './change-password.component';
import {DashboardsComponent} from './dashboards.component';
import {InformationComponent} from './information.component';
import {HelpComponent} from './help.component';
import {LicenseComponent} from './license.component';
import {AnalyzerPluginComponent} from './analyzer-plugin.component';
import {EvaluatorPluginComponent} from './evaluator-plugin.component';
// Administration backend
import {DashboardsAdminComponent} from './admin/dashboards-admin.component';
import {ProjectsAdminComponent} from './admin/projects-admin.component';
import {CreateProjectComponent} from './admin/create-project.component';
import {EditProjectComponent} from './admin/edit-project.component';
import {SettingsAdminComponent} from './admin/settings-admin.component';
import {UsersAdminComponent} from './admin/users-admin.component';
import {BackendAdminComponent} from './admin/backend-admin.component';
import {CreateUserComponent} from './admin/create-user.component';
import {EditUserComponent} from './admin/edit-user.component';
// Project pages
import {ProjectSummaryComponent} from './project/project-summary.component';
import {ProjectTrendsComponent} from './project/project-trends.component';
import {ProjectRunsComponent} from './project/project-runs.component';
// Run pages
import {ProjectRunSummaryComponent} from './project/run/project-run-summary.component';
import {ProjectRunAnalysisComponent} from './project/run/project-run-analysis.component';
import {ProjectRunDefectsComponent} from './project/run/project-run-defects.component';
import {ProjectRunStyleComponent} from './project/run/project-run-style.component';
import {ProjectRunMetricsComponent} from './project/run/project-run-metrics.component';
import {ProjectRunDesignComponent} from './project/run/project-run-design.component';
import {ProjectRunArchitectureComponent} from './project/run/project-run-architecture.component';
import {ProjectRunTrendsComponent} from './project/run/project-run-trends.component';
// File pages
import {FileSummaryComponent} from './project/run/file/file-summary.component';
import {FileAnalysisComponent} from './project/run/file/file-analysis.component';
import {FileIssuesComponent} from './project/run/file/file-issues.component';

/**
 * This component is Purifinity's central application.
 */
@Component({
    selector: 'app',
    directives: [
        HeaderComponent,
        FooterComponent,
        ROUTER_DIRECTIVES
    ],
    template:
    `<header></header>
<alerter></alerter>
<router-outlet></router-outlet>
<footer></footer>`
})
@RouteConfig([
    // Main Application
    { path: '/', name: 'Home', component: HomeComponent, useAsDefault: true },
    { path: '/login', name: 'Login', component: LoginComponent },
    { path: '/account', name: 'Account', component: AccountComponent },
    { path: '/change-password', name: 'ChangePassword', component: ChangePasswordComponent },
    { path: '/dashboards', name: 'Dashboards', component: DashboardsComponent },
    { path: '/information', name: 'Information', component: InformationComponent },
    { path: '/help', name: 'Help', component: HelpComponent },
    { path: '/license', name: 'License', component: LicenseComponent },
    { path: '/analyzer-plugins/:pluginId', name: 'AnalyzerPlugin', component: AnalyzerPluginComponent },
    { path: '/evaluator-plugins/:pluginId', name: 'EvaluatorPlugin', component: EvaluatorPluginComponent },
    // Administration Backend
    { path: '/admin', name: 'Admin', redirectTo: ['/DashboardsAdmin'] },
    { path: '/admin/dashboards', name: 'DashboardsAdmin', component: DashboardsAdminComponent },
    { path: '/admin/projects', name: 'ProjectsAdmin', component: ProjectsAdminComponent },
    { path: '/admin/projects/create', name: 'CreateProject', component: CreateProjectComponent },
    { path: '/admin/projects/:projectId/edit', name: 'EditProject', component: EditProjectComponent },
    { path: '/admin/settings', name: 'SettingsAdmin', component: SettingsAdminComponent },
    { path: '/admin/users', name: 'UsersAdmin', component: UsersAdminComponent },
    { path: '/admin/users/create', name: 'CreateUser', component: CreateUserComponent },
    { path: '/admin/users/:email/edit', name: 'EditUser', component: EditUserComponent },
    { path: '/admin/backend', name: 'BackendAdmin', component: BackendAdminComponent },
    // Project pages
    { path: '/projects/:projectId/summary', name: 'ProjectSummary', component: ProjectSummaryComponent },
    { path: '/projects/:projectId/trends', name: 'ProjectTrends', component: ProjectTrendsComponent },
    { path: '/projects/:projectId/runs', name: 'ProjectRuns', component: ProjectRunsComponent },
    // Run pages
    { path: '/projects/:projectId/runs/:runId/summary', name: 'ProjectRunSummary', component: ProjectRunSummaryComponent },
    { path: '/projects/:projectId/runs/:runId/analysis', name: 'ProjectRunAnalysis', component: ProjectRunAnalysisComponent },
    { path: '/projects/:projectId/runs/:runId/defects', name: 'ProjectRunDefects', component: ProjectRunDefectsComponent },
    { path: '/projects/:projectId/runs/:runId/style-issues', name: 'ProjectRunStyle', component: ProjectRunStyleComponent },
    { path: '/projects/:projectId/runs/:runId/metrics', name: 'ProjectRunMetrics', component: ProjectRunMetricsComponent },
    { path: '/projects/:projectId/runs/:runId/design', name: 'ProjectRunDesign', component: ProjectRunDesignComponent },
    { path: '/projects/:projectId/runs/:runId/architecture', name: 'ProjectRunArchitecture', component: ProjectRunArchitectureComponent },
    { path: '/projects/:projectId/runs/:runId/trends', name: 'ProjectRunTrends', component: ProjectRunTrendsComponent },
    // File pages
    { path: '/projects/:projectId/runs/:runId/files/:hashId/summary', name: 'FileSummary', component: FileSummaryComponent },
    { path: '/projects/:projectId/runs/:runId/files/:hashId/analysis', name: 'FileAnalysis', component: FileAnalysisComponent },
    { path: '/projects/:projectId/runs/:runId/files/:hashId/issues', name: 'FileIssues', component: FileIssuesComponent },
])
export class AppComponent { }
