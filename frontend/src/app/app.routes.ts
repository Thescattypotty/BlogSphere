import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { DashboardComponent } from './pages/dashboardPages/dashboard/dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { authGuard } from './guards/auth.guard';
import { TagComponent } from './pages/tag/tag.component';
import { TagsDashboardComponent } from './pages/dashboardPages/tags-dashboard/tags-dashboard.component';
import { PostsDashboardComponent } from './pages/dashboardPages/posts-dashboard/posts-dashboard.component';
import { PostComponent } from './pages/post/post.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {
        path: 'tags/:id', component: TagComponent
    },
    {
        path: ':id', component: PostComponent 
    },
    {
        path: 'dashboard',
        canActivate: [authGuard],
        children:[
            {
                path: '', component: DashboardComponent
            },
            {
                path: 'tags', component: TagsDashboardComponent,
            },
            {
                path: 'posts', component: PostsDashboardComponent,
            },
            {
                path: 'users', component: DashboardComponent,
                children: [
                    {
                        path: ':id', component: DashboardComponent
                    }
                ]
            },
        ]
    },
];

