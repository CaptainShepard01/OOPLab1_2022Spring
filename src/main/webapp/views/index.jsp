<%@ page import="ua.university.models.Teacher" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: balanton
  Date: 5/3/2022
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Faculty Application</title>
</head>

<style>
    body {
        --nav-width: 200px;
        margin: 0 0 0 var(--nav-width);
        font-family: 'Quicksand', sans-serif;
        font-size: 18px;
    }

    .nav {
        position: fixed;
        top: 0;
        left: 0;
        width: var(--nav-width);
        height: 100vh;
        background: #222222;
    }

    .nav__link {
        display: block;
        padding: 12px 18px;
        text-decoration: none;
        color: #eeeeee;
        font-weight: 500;
    }

    .nav__link:hover {
        background: rgba(255, 255, 255, 0.05);
    }

    #app {
        margin: 2em;
        line-height: 1.5;
        font-weight: 500;
    }

    a {
        color: #009579;
    }

    .models-table {
        cursor: pointer;
    }
</style>

<body>
<nav class="nav flex-column">
    <a href="/" class="nav__link" data-link>Dashboard</a>

    <a href="/teachers" class="nav__link" data-link>Teaches</a>
    <a href="/courses" class="nav__link" data-link>Courses</a>
    <a href="/students" class="nav__link" data-link>Students</a>
    <a href="/studentCourseRelations" class="nav__link" data-link>Students--Courses</a>

    <a href="/rocket" class="nav__link" data-link>Rocket!</a>
</nav>

<div id="app">
    <% if (request.getAttribute("info") != null) { %>
    <%= request.getAttribute("info") %>

    <%@include file="modelViews/course.jsp"%>
    <%@include file="modelViews/teacher.jsp"%>
    <%@include file="modelViews/student.jsp"%>
    <%@include file="modelViews/studentCourseRelations.jsp"%>
    <% } %>



</div>

<script type="javascript">
    class AbstractView {
        constructor(params) {
            this.params = params;
        }

        setTitle(title) {
            document.title = title;
        }

        async getHtml() {
            return "";
        }
    }

    class Dashboard extends AbstractView {
        constructor(params) {
            super(params);
            this.setTitle("Dashboard");
        }

        async getHtml() {
            return `
            <h1>Welcome back, Dom</h1>
            <p>
                Fugiat voluptate et nisi Lorem cillum anim sit do eiusmod occaecat irure do. Reprehenderit anim fugiat sint exercitation consequat. Sit anim laborum sit amet Lorem adipisicing ullamco duis. Anim in do magna ea pariatur et.
            </p>
            <p>
                <a href="/posts" data-link>View recent posts</a>.
            </p>
        `;
        }
    }

    class Posts extends AbstractView {
        constructor(params) {
            super(params);
            this.setTitle("Posts");
        }

        async getHtml() {
            return `
            <h1>Posts</h1>
            <p>You are viewing the posts!</p>
        `;
        }
    }

    class PostView extends AbstractView {
        constructor(params) {
            super(params);
            this.postId = params.id;
            this.setTitle("Viewing Post");
        }

        async getHtml() {
            return `
            <h1>Post</h1>
            <p>You are viewing post #${this.postId}.</p>
        `;
        }
    }

    class Teachers extends AbstractView {
        constructor(params) {
            super(params);
            this.setTitle("Teachers");
        }

        async getHtml() {
            return `
            <h1>Teachers</h1>
            <p>You are viewing the posts!</p>
        `;
        }
    }

    class TeacherView extends AbstractView {
        constructor(params) {
            super(params);
            this.postId = params.id;
            this.setTitle("Viewing Teacher");
        }

        async getHtml() {
            return `
            <h1>Teacher</h1>
            <p>You are viewing post #${this.postId}.</p>
        `;
        }
    }

    class Settings extends AbstractView {
        constructor(params) {
            super(params);
            this.setTitle("Settings");
        }

        async getHtml() {
            return `
            <h1>Settings</h1>
            <p>Manage your privacy and configuration.</p>
        `;
        }
    }

    const pathToRegex = path => new RegExp("^" + path.replace(/\//g, "\\/").replace(/:\w+/g, "(.+)") + "$");

    const getParams = match => {
        const values = match.result.slice(1);
        const keys = Array.from(match.route.path.matchAll(/:(\w+)/g)).map(result => result[1]);

        return Object.fromEntries(keys.map((key, i) => {
            return [key, values[i]];
        }));
    };

    const navigateTo = url => {
        history.pushState(null, null, url);
        router();
    };

    const router = async () => {
        const routes = [
            {path: "/", view: Dashboard},
            {path: "/teacher", view: Posts},
            {path: "/teacher/:id", view: PostView},
            {path: "/student", view: Posts},
            {path: "/student/:id", view: PostView},
            {path: "/course", view: Posts},
            {path: "/course/:id", view: PostView},
            {path: "/studentCourseRelation", view: Posts},
            {path: "/studentCourseRelation/:id", view: PostView},
            {path: "/settings", view: Settings}
        ];

        // Test each route for potential match
        const potentialMatches = routes.map(route => {
            return {
                route: route,
                result: location.pathname.match(pathToRegex(route.path))
            };
        });

        let match = potentialMatches.find(potentialMatch => potentialMatch.result !== null);

        if (!match) {
            match = {
                route: routes[0],
                result: [location.pathname]
            };
        }

        const view = new match.route.view(getParams(match));

        document.querySelector("#app").innerHTML = await view.getHtml();
    };

    window.addEventListener("popstate", router);

    document.addEventListener("DOMContentLoaded", () => {
        document.body.addEventListener("click", e => {
            if (e.target.matches("[data-link]")) {
                e.preventDefault();
                navigateTo(e.target.href);
            }
        });

        router();
    });

</script>
</body>
</html>