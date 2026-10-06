<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="nav">
            <ul class="navbar-nav me-auto">

                <li class="nav-item">
                    <a class="nav-link" href="Controller?action=companies">Companies</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="Controller?action=students">Students</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="Controller?action=tracking">Reviews</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="Controller?action=internships">Internships</a>
                </li>

            </ul>

            <form action="Controller" method="get">
                <input type="hidden" name="action" value="logout"/>
                <button class="btn btn-danger btn-sm">Logout</button>
            </form>

        </div>
    </div>
</nav>